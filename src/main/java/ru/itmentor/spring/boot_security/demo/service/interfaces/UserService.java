package ru.itmentor.spring.boot_security.demo.service.interfaces;

import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(Long id);

    void cleanUsersTable();

    User getUserById(Long id);

    User getUserByEmail(String email);

    Set<Role> getAllRoles();

    Set<Role> findRoleById(Set<Long> id);

    Role findRoleByStringId(Long id);
}
