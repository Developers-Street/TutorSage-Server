package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserData;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    UserData saveUserData(UserData userData);

    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
