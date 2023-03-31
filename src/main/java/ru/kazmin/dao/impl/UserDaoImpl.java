package ru.kazmin.dao.impl;

import org.springframework.stereotype.Repository;
import ru.kazmin.dao.abstracts.UserDao;
import ru.kazmin.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext()
    private EntityManager entityManager;
    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return (User)entityManager.createQuery("from User u where u.username = :username")
                .setParameter("username", username).getResultList().get(0);
    }
}
