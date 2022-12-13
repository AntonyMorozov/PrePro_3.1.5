package ru.kata.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.exception.UserAlreadyExistException;
import ru.kata.spring.model.User;
import ru.kata.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class RestAdminController {

    private UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> usersList = userService.getUsers();
        if (usersList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usersList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") long userId) {
        if (userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        User showUser = userService.getUserById(userId);
        return ResponseEntity.ok().body(showUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) throws UserAlreadyExistException {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserAlreadyExistException {
        if (userService.getUserById(user.getId()) == null){
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(user, user.getId());
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        if (userService.getUserById(id) == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
