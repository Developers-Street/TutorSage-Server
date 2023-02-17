package com.developersstreet.tutorsage.api;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developersstreet.tutorsage.dto.OrganizationDTO;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Lecture;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.Test;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.CourseService;
import com.developersstreet.tutorsage.service.LectureService;
import com.developersstreet.tutorsage.service.OrganizationService;
import com.developersstreet.tutorsage.service.TestService;
import com.developersstreet.tutorsage.service.UserService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {
    
    private final OrganizationService organizationService;
    private final UtilityService utilityService;
    private final UserService userService;
    private final CourseService courseService;
    private final LectureService lectureService;
    private final TestService testService;
    
    @GetMapping("/")
    public void getOrganizations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String query = request.getParameter("query");
            Long offset = Long.parseLong(request.getParameter("offset"));
            Long limit = Long.parseLong(request.getParameter("limit"));
            List<Organization> organizations = organizationService.getOrganizationsByQueryAndOffsetAndLimit(query, offset, limit);
            new ObjectMapper().writeValue(response.getOutputStream(), organizations);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
    
    @PostMapping("/create")
    public void createOrganization(@RequestBody Organization o, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            organizationService.createOrganization(o, user);
        } catch(Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
    
    @PostMapping("/{organizationId}/course/create")
    public void createCourse(@PathVariable Long organizationId, @RequestBody Course c, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		Course savedCourse = courseService.createCourse(c, user, organizationId);
    		new ObjectMapper().writeValue(response.getOutputStream(), savedCourse);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    
    @PostMapping("/{organizationId}/course/{courseId}/subjects/add")
    public void addSubjectsToCourse(@PathVariable Long organizationId, @PathVariable Long courseId, @RequestBody Set<Subject> subjects, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		courseService.addSubjectsToCourse(organizationId, courseId, user, subjects);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    @GetMapping("/{id}/courses")
    public void getCourses(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		String query = request.getParameter("query");
    		Long offset = Long.parseLong(request.getParameter("offset"));
    		Long limit  = Long.parseLong(request.getParameter("limit"));
    		Set<Course> courses = courseService.getCoursesByOrganizationId(id, query, offset, limit);
    		new ObjectMapper().writeValue(response.getOutputStream(), courses);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    @PostMapping("/{organizationId}/course/{courseId}/subject/{subjectId}/lecture/create")
    public void addLectureToSubject(@PathVariable Long courseId, @PathVariable Long subjectId, @RequestBody Lecture lecture, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		lectureService.addLectureToSubject(lecture, courseId, subjectId, user);
    	} catch (Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
		}
    }
    
    @PostMapping("/{organizationId}/course/{courseId}/subject/{subjectId}/test/create")
    public void createTestInSubject(@PathVariable Long subjectId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		Test newTest = testService.createTest();
    		new ObjectMapper().writeValue(response.getOutputStream(), newTest);
    	} catch (Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
		}
    }

    @GetMapping("/{id}")
    public void getOneOrganization(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	OrganizationDTO organizationDTO = organizationService.getOrganizationDetails(id);
            new ObjectMapper().writeValue(response.getOutputStream(), organizationDTO);
        } catch(Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
    
    
    //TO CHECK AND REFACTOR
    @PostMapping("/join")
    public void joinOrganization(@RequestParam Long organizationId, @RequestParam Long roleId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		Role role = userService.getRoleById(roleId);
    		organizationService.joinOrganization(organizationId, user, role);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    @PostMapping("/student/join")
    public void joinOrganizationAsStudent(@RequestParam Long organizationId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		organizationService.joinOrganizationAsStudent(organizationId, user);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    @PostMapping("/{organizationId}/course/{courseId}/join")
    public void joinCourseAsStudent(@PathVariable Long organizationId, @PathVariable Long courseId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		courseService.joinCourseAsStudent(organizationId, courseId, user);
    	} catch (Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
}