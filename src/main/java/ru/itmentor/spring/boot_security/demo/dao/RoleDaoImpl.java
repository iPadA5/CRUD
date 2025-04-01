package ru.itmentor.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.dao.interfaces.RoleDao;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);
    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Role> findByName(String name) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw new NoResultException("Role with name " + name + " not found");
        }
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return  query.getResultList();
    }

    @Override
    public List<Role> findById(List<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.id IN :roleIds", Role.class)
                .setParameter("roleIds", roleIds)
                .getResultList();
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
