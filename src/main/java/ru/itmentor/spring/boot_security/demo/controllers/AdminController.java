package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import ru.itmentor.spring.boot_security.demo.service.UserService;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/all-users";
    }

    @GetMapping("/all-users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all-users";
    }

    @PostMapping("/add")
    public String addUser(User user,
                          @RequestParam(required = false) Boolean ROLE_ADMIN,
                          @RequestParam(required = false) Boolean ROLE_USER) {

        boolean adminIsChecked = ROLE_ADMIN != null && ROLE_ADMIN;
        boolean userIsChecked = ROLE_USER != null && ROLE_USER;

        userService.saveUser(user, adminIsChecked, userIsChecked);
        return "redirect:/admin/all-users";
    }

    @PostMapping("/update")
    public String updateUser(User user,
                             @RequestParam(value = "roles[]", required = false) Set<Role> roles) {
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        Set<Role> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/user/{id}")
    public String getUserByEmail(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
