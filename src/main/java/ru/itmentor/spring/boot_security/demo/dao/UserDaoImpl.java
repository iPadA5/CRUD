package ru.itmentor.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.interfaces.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    private RoleDaoImpl roleDaoImpl;

    @Autowired
    UserDaoImpl(RoleDaoImpl roleDaoImpl) {
        this.roleDaoImpl = roleDaoImpl;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        try{
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while getting all users");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void cleanUsersTable() {
        entityManager.createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            typedQuery.setParameter("email", email);
            Hibernate.initialize(typedQuery.getSingleResult().getRoles());
            return typedQuery.getSingleResult();
        } catch (Exception e){
            System.out.println("Error while getting user by email");
        }
        return null;
    }


    private Role findRoleByName(String roleName) {
        return roleDaoImpl.findByName(roleName).orElseThrow(() -> new RuntimeException("no role found"));
    }

}
