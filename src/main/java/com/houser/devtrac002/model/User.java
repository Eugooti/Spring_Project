package com.houser.devtrac002.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

import java.util.Collection;
import com.houser.devtrac002.model.Role;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames ="userLogon" ))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstname;

    @Column(name = "lastName")
    private String lastname;

    @Column(name = "useerpassword")
    private String userPassword;

    @Column(name = "userlogon", unique = true)
    private String userLogon;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "userroles",joinColumns = @JoinColumn(name = "UserID",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "roleID",referencedColumnName = "id"))



    private Collection<Role> roles;

    public User(){

    }

    public  User(String firstname,String lastname,String email,String password,Collection<Role> roles){
        super();
        this.firstname=firstname;
        this.lastname=lastname;
        this.userLogon=email;
        this.userPassword=password;
        this.roles=roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserLogon() {
        return userLogon;
    }

    public void setUserLogon(String email) {
        this.userLogon = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
