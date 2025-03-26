package ru.itmentor.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

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
    public User getUserByEmail(String email) {
        try {
            TypedQuery<User> typedQuery;
            typedQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            typedQuery.setParameter("email", email);
            return typedQuery.getSingleResult();
        } catch (Exception e){
            System.out.println("Error while getting user by email");
        }
        return null;
    }

}
