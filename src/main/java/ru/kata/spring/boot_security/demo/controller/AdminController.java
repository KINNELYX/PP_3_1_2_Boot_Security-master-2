package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/admin/addUser")
    public String addUser(Model model) {
        User user = new User();
        Role role = new Role();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "add-user";
    }

    @RequestMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("users") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/admin/updateUser/{id}")
    public String updateUser(@PathVariable(name = "id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("users", user);
        return "update-user";
    }

    @RequestMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("users", user);
        return "usersById";
    }
}
