package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.UserDto;
import edu.akorzh.survey.api.mapper.UserMapper;
import edu.akorzh.survey.common.UserRoles;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/signup/{id}")
    public UserDto register(@Validated @RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        final User user = userMapper.to(userDto);
        userService.register(user, id);
        return userMapper.to(user);
    }

    @GetMapping("/users/")
    @RolesAllowed("ROLE_UNIVERSITY_ADMINISTRATOR")
    public Page<UserDto> getUsers(Authentication authentication,
                                  @PageableDefault(sort = {"login"},
                                          direction = Sort.Direction.DESC, size = 20) final Pageable pageable) {
        return new PageImpl<>(userService.getUsers(authentication.getName(), pageable)
                .stream()
                .map(userMapper::to)
                .collect(Collectors.toList()));
    }

    @PostMapping("/users/{id}")
    @RolesAllowed("ROLE_UNIVERSITY_ADMINISTRATOR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable(value = "id") Long userId) {
        userService.confirm(userId);
    }

    @PostMapping("/users/")
    @RolesAllowed("ROLE_UNIVERSITY_ADMINISTRATOR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmAll(Authentication authentication) {
        userService.confirmAll(authentication);
    }

}
