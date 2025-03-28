package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;

public interface UserService {

    void saveUser(User user, boolean adminIsChecked, boolean userIsChecked);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(Long id);

    void cleanUsersTable();

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<Role> getAllRoles();

    List<Role> findRoleById(List<Long> id);

    Role findRoleByStringId(Long id);
}
