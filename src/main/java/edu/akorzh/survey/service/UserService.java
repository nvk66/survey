package edu.akorzh.survey.service;

import edu.akorzh.survey.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    void confirm(Long userId);

    void confirmAll();

    User getUser(String login);

    List<User> getUsers(String userName);
}
