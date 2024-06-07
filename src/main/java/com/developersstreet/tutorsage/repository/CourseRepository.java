package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.enums.CourseVisibilityType;
import com.developersstreet.tutorsage.model.organization.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseById(Long Id);
    List<Course> findCoursesByNameContainsAndVisibility(String query, CourseVisibilityType visibility);
}