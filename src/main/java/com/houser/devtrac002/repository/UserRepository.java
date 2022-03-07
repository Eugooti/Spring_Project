package com.houser.devtrac002.repository;


import com.houser.devtrac002.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   // User findByEmail(String email);

    User findByUserLogon(String username);
}
