package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.interfaces.UserService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.copyDataFromUser(user);
                    return userDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.copyDataFromUser(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User added successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try{
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User could not be deleted");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User updatedUser = userService.getUserById(user.getId());
        updatedUser.copyUser(user);
        userService.updateUser(updatedUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body("User updated successfully");
    }
}
