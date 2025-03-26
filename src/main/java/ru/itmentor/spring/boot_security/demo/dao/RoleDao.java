package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
