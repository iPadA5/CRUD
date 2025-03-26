package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(Long id);

    void cleanUsersTable();

    User getUserById(Long id);

    User getUserByEmail(String email);
}
