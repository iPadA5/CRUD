package ru.itmentor.spring.boot_security.demo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.roles.Role;
import ru.itmentor.spring.boot_security.demo.service.interfaces.UserService;

@Component
public class StringToRoleConverter implements Converter<String, Role> {

    private final UserService userService;

    @Autowired
    StringToRoleConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Role convert(String source) {
        return userService.findRoleByStringId(Long.parseLong(source));
    }
}
