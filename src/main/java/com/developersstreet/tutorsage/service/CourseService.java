package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.dto.UserDTO;
import com.developersstreet.tutorsage.model.organization.Course;
import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course createCourse(Course course, User user, Long organizationId) throws Exception;
    Course getCourseById(Long id);
    void joinCourseAsStudent(Long organizationId, Long courseId, User user) throws Exception;
    void addSubjectsToCourse(Long organizationId, Long courseId, User user, Set<Subject> subjects) throws Exception;
    List<Course> getCoursesByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
    Set<Course> getCoursesByOrganizationId(Long organizationId, String query, Long offset, Long limit) throws Exception;
    Set<UserDTO> getCourseNonAddedStudents(Long organizationId, Long courseId) throws Exception;
    String addStudentsToCourse(Long organizationId, Long courseId, Set<Long> studentsId, User user) throws Exception;
    Course addMemberToCourse(Long courseId, User user) throws Exception;
    boolean isUserHeadTutor(Course course, User user);
}
