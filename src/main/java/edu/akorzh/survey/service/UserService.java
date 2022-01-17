package edu.akorzh.survey.service;

import edu.akorzh.survey.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void register(User user, Long id);

    void confirm(Long userId);

    void confirmAll(Authentication authentication);

    User getUser(String login);

    List<User> getUsers(String userName, Pageable pageable);
}
