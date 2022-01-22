package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByUsernameContains(String q1);
    User findByUsername(String Username);
    User findByEmail(String Email);
    User findUserById(Long Id);
}
