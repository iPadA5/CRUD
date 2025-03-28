package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/all-users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all-users";
    }

    @PostMapping("/admin/add")
    public String addUser(User user,
                          @RequestParam(required = false) Boolean ROLE_ADMIN,
                          @RequestParam(required = false) Boolean ROLE_USER) {

        boolean adminIsChecked = ROLE_ADMIN != null && ROLE_ADMIN;
        boolean userIsChecked = ROLE_USER != null && ROLE_USER;

        userService.saveUser(user, adminIsChecked, userIsChecked);
        return "redirect:/admin/all-users";
    }
    @PostMapping("/admin/update")
    public String updateUser(User user,
                             @RequestParam(value = "roles[]", required = false) List<Role> roles) {
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "edit";
    }
    @GetMapping("/admin/user/{email}")
    public String getUserByEmail(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping("/user")
    public String getCurrentUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            User user = userService.getUserByEmail(userDetails.getUsername());
            model.addAttribute("user", user);
        }
        return "user";
    }
}
