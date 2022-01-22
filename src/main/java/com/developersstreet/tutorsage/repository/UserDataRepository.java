package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
//    UserData findByUserId(Long userId);
    UserData findUserDataById(Long Id);
}
