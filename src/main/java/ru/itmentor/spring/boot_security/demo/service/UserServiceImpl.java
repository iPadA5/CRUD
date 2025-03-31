package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.itmentor.spring.boot_security.demo.dao.UserDaoImpl;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserDaoImpl userDao;
    private final RoleDaoImpl roleDaoImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserDaoImpl userDao, RoleDaoImpl roleDaoImpl, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDaoImpl = roleDaoImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user, boolean adminIsChecked, boolean userIsChecked) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user, adminIsChecked, userIsChecked);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDaoImpl.getAllRoles();
    }

    @Override
    public List<Role> findRoleById(List<Long> id) {
        return roleDaoImpl.findById(id);
    }

    @Override
    public Role findRoleByStringId(Long id) {
        return roleDaoImpl.findById(id);
    }
}
