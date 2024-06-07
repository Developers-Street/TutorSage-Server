package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByUsernameContains(String q1);
    User findByUsername(String Username);
    User findByEmail(String Email);
    User findUserById(Long Id);
}
