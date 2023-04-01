package ru.kazmin.service.abstracts;

import ru.kazmin.models.User;

import java.util.List;

public interface UserService {
    void setOperatorRole(User user);
    List<User> getUsers();
    User getUser(String username);
}
