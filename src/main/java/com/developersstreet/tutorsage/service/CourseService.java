package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course createCourse(Course c, User user, Long organizationId);
    Course getCourseById(Long id);
    void joinCourseAsStudent(Long organizationId, Long courseId, User user) throws Exception;
    void addSubjectsToCourse(Long organizationId, Long courseId, User user, Set<Subject> subjects) throws Exception;
    List<Course> getCoursesByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
    Course addMemberToCourse(Long courseId, User user) throws Exception;
    boolean isUserHeadTutor(Course course, User user);
}
