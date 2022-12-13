package ru.kata.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.dao.UserDao;
import ru.kata.spring.exception.UserAlreadyExistException;
import ru.kata.spring.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user) throws UserAlreadyExistException {
        if (userDao.getUserByName(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that nickname: "
                    + user.getUsername());
        }

        if (userDao.getUserByMail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, long id) throws UserAlreadyExistException {
        if (userDao.getUserByName(user.getUsername()).isPresent() & !userDao.getUserById(id).getUsername().equals(user.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that nickname: "
                    + user.getUsername());
        }

        if (userDao.getUserByMail(user.getEmail()).isPresent() & !userDao.getUserById(id).getEmail().equals(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.updateUser(user, id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.getUserByMail(name).orElse(null);
    }
}
