package ru.kazmin.dao.abstracts;

import ru.kazmin.models.User;

import java.util.List;

public interface UserDao {
    void setOperatorRole(User user);
    List<User> getUsers();
    User findByUsername(String username);
}
