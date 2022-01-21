package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.common.UserRoles;
import edu.akorzh.survey.exception.DuplicateException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Role;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.repository.*;
import edu.akorzh.survey.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PupilRepository pupilRepository;
    private final TeacherRepository teacherRepository;
    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> {
                    log.error("User with login {} not found", login);
                    throw new UsernameNotFoundException("Пользователь не найден.");
                });

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().name())));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }

    @Transactional
    @Override
    public void register(User user, Long id) {
        University university = universityRepository.findById(id).orElseThrow(NotFoundException::new);
        user.setUniversity(university);
        user.setConfirmed(false);
        if (userRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new DuplicateException();
        }
        if (userRepository.findUserByEmail(user.getLogin()).isPresent()) {
            throw new DuplicateException();
        }
        user.setRoles(getDefaultRoles());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void confirm(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        if (pupilRepository.findByUser(user) != null) {
            user.getRoles().add(getRole(UserRoles.ROLE_PUPIL));
        }
        if (teacherRepository.findByUser(user) != null) {
            user.getRoles().add(getRole(UserRoles.ROLE_TEACHER));
        }
        user.getRoles().remove(getRole(UserRoles.ROLE_USER_NOT_CONFIRMED));
        user.setConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public void confirmAll(Authentication authentication) {
        User user = userRepository.findUserByLogin(authentication.getName()).orElseThrow(NotFoundException::new);
        List<User> users = userRepository.findUserByUniversityAndConfirmedFalse(user.getUniversity());
        users.forEach(u -> confirm(u.getId()));
    }

    @Override
    public User getUser(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> getUsers(String userName, Pageable pageable) {
        User user = userRepository.findUserByLogin(userName).orElseThrow(NotFoundException::new);
        return userRepository.findUsersByUniversityAndConfirmedFalse(user.getUniversity(), pageable);
    }

    private Role getRole(UserRoles role) {
        return roleRepository.findByName(role).orElseThrow(NotFoundException::new);
    }

    private Set<Role> getDefaultRoles() {
        return Set.of(getRole(UserRoles.ROLE_USER), getRole(UserRoles.ROLE_USER_NOT_CONFIRMED));
    }
}
