package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.UserDto;
import edu.akorzh.survey.api.mapper.UserMapper;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/")
    public UserDto register(@Validated @RequestBody UserDto userDto) {
        final User user = userMapper.to(userDto);
        userService.register(user);
        return userMapper.to(user);
    }

}
