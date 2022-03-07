package com.houser.devtrac002.service;

import com.houser.devtrac002.model.Role;
import com.houser.devtrac002.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User>getAllUsers();
    void SaveUser(User user);
    User save(User registrationDto);
    User getUserById(Long id);
    void deleteUserById(Long id);
    List<Role>listRoles();

}
