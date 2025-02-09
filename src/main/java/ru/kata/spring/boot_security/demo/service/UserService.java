package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    void saveUser(User user);

    List<User> getAll();

    User getById(Long id);

    void update(User user);

    void delete(Long id);


    User findByUsername(String username);

    List <Role> findAll();

}
