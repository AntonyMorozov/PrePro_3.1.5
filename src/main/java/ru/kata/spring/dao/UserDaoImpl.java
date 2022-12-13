package ru.kata.spring.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.kata.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user, long id) {
        User updatedUser = getUserById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRoles(user.getRoles());
        return em.merge(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return em.unwrap(Session.class).createQuery("select n from User n where n.username =: username", User.class)
                .setParameter("username", name)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        return em.unwrap(Session.class).createQuery("select m from User m where m.email =: mail", User.class)
                .setParameter("mail", mail)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }
}
