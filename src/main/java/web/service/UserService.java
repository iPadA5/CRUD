package web.service;

import web.model.User;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(Long id);

    void cleanUsersTable();

    User getUserById(Long id);
}
