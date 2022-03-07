package com.houser.devtrac002.service;


import com.houser.devtrac002.model.Role;
import com.houser.devtrac002.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public interface RoleServicelmpl extends RoleService {

    @Autowired
    RoleRepository roleRepository = null;

    @Override
    public default List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    public default void saveRole(Role role){
        this.roleRepository.save(role);
    }

    public default Role getRoleById(long id){
        return roleRepository.getById(id);
    }

    @Override
    public default void deleteRoleById(long id){
        roleRepository.deleteAllById(Collections.singleton(id));
    }


}
