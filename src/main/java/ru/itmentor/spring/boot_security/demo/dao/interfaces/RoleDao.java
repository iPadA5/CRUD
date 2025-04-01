package ru.itmentor.spring.boot_security.demo.dao.interfaces;

import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleDao {

    Optional<Role> findByName(String name);

    Set<Role> getAllRoles();

    Set<Role> findById(Set<Long> roleIds);

    Role findById(Long id);

}
