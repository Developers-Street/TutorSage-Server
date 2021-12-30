package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
