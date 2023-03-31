package ru.kazmin.dao.abstracts;

import ru.kazmin.models.User;

public interface UserDao {
    User getUser(Long id);
    User findByUsername(String username);
}
