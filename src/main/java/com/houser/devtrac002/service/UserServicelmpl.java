package com.houser.devtrac002.service;


import com.houser.devtrac002.model.Role;
import com.houser.devtrac002.model.User;
import com.houser.devtrac002.repository.RoleRepository;
import com.houser.devtrac002.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicelmpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void SaveUser(User user) {
        this.userRepository.save(user);

    }

    @Override
    public User save(User registration) {
        User user=new User(registration.getFirstname(), registration.getLastname(),registration.getUserLogon(),passwordEncoder.encode(registration.getUserPassword()), Arrays.asList(new Role("BLANKROLE")));
        return userRepository.save(registration);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optional=userRepository.findById(id);
        User user=null;
        if(optional.isPresent()){
            user=optional.get();
        }
        else {
            throw new RuntimeException("User not fund for id::"+id);
        }
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);

    }

    @Override
    public List<Role> listRoles() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserLogon(username);
        if (user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        assert mapRolesToAuthorities(user.getRoles()) != null;
        return new
                org.springframework.security.core.userdetails.User(user.getUserLogon(),user.getUserPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserServicelmpl(UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }




}
