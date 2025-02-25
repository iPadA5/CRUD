package web.service;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(Long id);

    void cleanUsersTable();

    User getUserById(Long id);
}
