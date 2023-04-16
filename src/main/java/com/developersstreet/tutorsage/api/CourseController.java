package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.organization.Course;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.service.CourseService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final UtilityService utilityService;

    @GetMapping("/")
    public void getCourses(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String query = request.getParameter("query");
            Long offset = Long.parseLong(request.getParameter("offset"));
            Long limit = Long.parseLong(request.getParameter("limit"));
            List<Course> courses = courseService.getCoursesByQueryAndOffsetAndLimit(query, offset, limit);
            new ObjectMapper().writeValue(response.getOutputStream(), courses);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @GetMapping("/{id}")
    public void getOneCourse(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Course c = courseService.getCourseById(id);
            if(c == null) throw new Exception("Course not found");
            new ObjectMapper().writeValue(response.getOutputStream(), c);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @PostMapping("/create")
    public void createCourse(@RequestBody Course c, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            courseService.createCourse(c, user, null);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @PostMapping("/join")
    public void joinCourse(@RequestBody JoinCourseForm courseId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            // User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            // Course c = courseService.addMemberToCourse(courseId.getCourseId(), user);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}

@Data
class JoinCourseForm {
    private Long courseId;
}