package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
    User findByEmail(String Email);
}
