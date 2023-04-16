package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.user.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
//    UserData findByUserId(Long userId);
    UserData findUserDataById(Long Id);
}
