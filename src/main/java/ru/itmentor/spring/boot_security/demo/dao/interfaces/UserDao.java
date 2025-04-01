package ru.itmentor.spring.boot_security.demo.dao.interfaces;

import ru.itmentor.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {

    void saveUser(User user, boolean adminIsChecked, boolean userIsChecked);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    void updateUser(User user);

    void cleanUsersTable();

    User getUserById(Long id);

    User getUserByEmail(String email);
}
