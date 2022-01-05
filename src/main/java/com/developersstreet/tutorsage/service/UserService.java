package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserData;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws Exception;
    Role saveRole(Role role);
    UserData saveUserData(UserData userData) throws Exception;

    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    UserData getUserDataByUser_id(Long user_id);
    List<User>getUsers();
}
