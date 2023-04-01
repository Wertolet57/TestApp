package ru.kazmin.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kazmin.dao.abstracts.UserDao;
import ru.kazmin.models.Role;
import ru.kazmin.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public void setOperatorRole(User user) {
        Role role = (Role) entityManager.createQuery("from Role r where r.authority = 'ROLE_OPERATOR'").getResultList().get(0);
        user.addRole(role);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return (User)entityManager.createQuery("from User u  JOIN FETCH u.roles where u.username = :username")
                .setParameter("username", username).getResultList().get(0);
    }
}
