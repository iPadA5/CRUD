package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import ru.itmentor.spring.boot_security.demo.service.interfaces.UserService;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public String createUser(@RequestBody User user,
                             @RequestParam Boolean ROLE_ADMIN,
                             @RequestParam Boolean ROLE_USER) {
        boolean adminIsChecked = ROLE_ADMIN != null && ROLE_ADMIN;
        boolean userIsChecked = ROLE_USER != null && ROLE_USER;
        try {
            userService.saveUser(user, adminIsChecked, userIsChecked);
            return "User created";
        } catch (Exception e) {
            return "User creation failed";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try{
            userService.deleteUserById(id);
            return "User deleted";
        } catch (Exception e) {
            return "User deletion failed";
        }
    }

    @PatchMapping("/update")
    public String updateUser(@RequestBody User user,
                             @RequestParam(value = "roles", required = false) Set<Role> roles) {
        try{
            user.setRoles(roles);
            userService.updateUser(user);
            return "User updated";
        } catch (Exception e) {
            return "User update failed";
        }
    }
}
