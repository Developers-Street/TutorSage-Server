package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserData;
import com.developersstreet.tutorsage.model.UserMerged;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws Exception;
    Role saveRole(Role role);
    UserData saveUserData(UserData userData) throws Exception;

    void addRoleToUser(String username, String roleName);
    UserMerged getUserMergedByUserId(String username, Long userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    UserData getUserDataByUserId(Long user_id);
    List<User>getUsers();
}
