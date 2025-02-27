package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    void updateUser(User user);

    void cleanUsersTable();

    User getUserById(Long id);
}
