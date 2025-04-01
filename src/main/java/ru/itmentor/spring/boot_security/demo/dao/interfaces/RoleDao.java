package ru.itmentor.spring.boot_security.demo.dao.interfaces;

import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import java.util.List;
import java.util.Optional;

public interface RoleDao {

    Optional<Role> findByName(String name);

    List<Role> getAllRoles();

    List<Role> findById(List<Long> id);

    Role findById(Long id);

}
