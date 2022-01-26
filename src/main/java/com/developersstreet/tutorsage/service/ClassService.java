package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.Quiz;
import com.developersstreet.tutorsage.model.User;

import java.util.List;

public interface ClassService {
    Class createClass(Class c);
    Class getClassById(Long id);
    List<Class> getClassesByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
    void addQuizToClass(Long classId, Quiz quiz);
    Class addMemberToClass(Long classId, User user) throws Exception;
}
