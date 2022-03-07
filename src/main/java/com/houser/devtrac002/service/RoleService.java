package com.houser.devtrac002.service;

import com.houser.devtrac002.model.Role;
import com.houser.devtrac002.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    void saveRole(Role role);
    Role getRoleById(Long id);
    void deleteRoleById(long id);
}
