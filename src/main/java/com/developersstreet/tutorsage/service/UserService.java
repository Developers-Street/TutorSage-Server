package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserData;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws Exception;
    Role saveRole(Role role);
    Role getRoleById(Long id);
    boolean isStudent(User user);
    void saveUserData(User user, UserData userData) throws Exception;
    void updateUserData(User user, UserData userData) throws Exception;

    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User>getUsersByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
    User getUserById(Long Id);
}
