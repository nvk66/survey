package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.common.UserRoles;
import edu.akorzh.survey.exception.DuplicateException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Role;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.repository.RoleRepository;
import edu.akorzh.survey.repository.UserRepository;
import edu.akorzh.survey.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
    public void register(User user) {
        log.info("!!! {}", user);
        if (userRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new DuplicateException();
        }
        if (userRepository.findUserByEmail(user.getLogin()).isPresent()) {
            throw new DuplicateException();
        }
        user.setRoles(getDefaultRoles());
        userRepository.save(user);
    }

    private Set<Role> getDefaultRoles() {
        return Set.of(roleRepository.findByName(UserRoles.ROLE_USER).orElseThrow(NotFoundException::new));
    }



}
