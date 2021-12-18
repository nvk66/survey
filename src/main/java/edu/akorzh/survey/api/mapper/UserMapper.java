package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.UserDto;
import edu.akorzh.survey.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto to(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .id(user.getId())
                .roles(roleMapper.to(user.getRoles()))
                .build();
    }

    public User to(UserDto user) {
        return User.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
    }

}
