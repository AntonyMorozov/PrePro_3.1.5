package ru.kata.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.exception.UserAlreadyExistException;
import ru.kata.spring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void addUser(User user) throws UserAlreadyExistException;

    User updateUser(User user, long id) throws UserAlreadyExistException;

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getUsers();

    Optional<User> getUserByName(String name);
}
