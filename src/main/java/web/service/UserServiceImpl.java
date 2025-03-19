package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;


    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }


    @Override
    @Transactional
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
