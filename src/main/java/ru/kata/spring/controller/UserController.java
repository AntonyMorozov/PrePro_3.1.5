package ru.kata.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.model.User;
import ru.kata.spring.service.RoleService;
import ru.kata.spring.service.UserService;

@Controller
@RequestMapping()
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showLoginPage(){
        return "/login";
    }

    @GetMapping("/user/{id}")
    public String showUserPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/user";
    }

    @GetMapping("/admin")
    public String showMainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", user);
        model.addAttribute("role", roleService.getAllRoles());
        return "users-page";
    }
}
