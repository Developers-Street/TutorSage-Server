package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.User;

public interface ClassService {
    Class createClass(Class c);
    void addMemberToClass(Long classId, User user);
}
