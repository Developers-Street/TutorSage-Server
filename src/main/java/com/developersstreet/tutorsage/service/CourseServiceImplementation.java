package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.dto.UserDTO;
import com.developersstreet.tutorsage.enums.CourseVisibilityType;
import com.developersstreet.tutorsage.model.organization.Course;
import com.developersstreet.tutorsage.model.organization.Organization;
import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    
    private final UserOrganizationRolesService userOrganizationRolesService;
    private final OrganizationService organizationService;
    private final SubjectService subjectService;
    private final UserService userService;

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public List<Course> getCoursesByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception {
        List<Course> courses = courseRepository.findCoursesByNameContainsAndVisibility(query, CourseVisibilityType.PUBLIC);
        if(courses.size() == 0) throw new Exception("No Courses found");
        Long fromIndex = limit * (offset - 1);
        Long toIndex = limit * (offset - 1) + limit;
        if(courses.size() < toIndex) courses = courses.subList(Integer.parseInt(fromIndex.toString()), courses.size());
        else courses = courses.subList(Integer.parseInt(fromIndex.toString()), Integer.parseInt(toIndex.toString()));
        return courses;
    }
    
    @Override
    public Set<Course> getCoursesByOrganizationId(Long organizationId, String query, Long offset, Long limit) {
    	Organization organization = organizationService.getOrganizationById(organizationId);
    	Set<Course> courses = organization.getCourses();
    	return courses;
    }

    @Override
    public Course createCourse(Course course, User user, Long organizationId) throws Exception {
    	Organization organization = null;
    	if(organizationId != null) {
    		organization = organizationService.getOrganizationById(organizationId);
    		if(!organizationService.isUserAdminOfOrganization(organization, user)) {
    			throw new Exception("You are not authorized to perform this task");
    		}
    		course.setVisibility(CourseVisibilityType.ORGANIZATION);
    	}
    	else {
    		course.setVisibility(CourseVisibilityType.PUBLIC);
    	}
    	if(!userOrganizationRolesService.isUserPartOfOrganization(organization, course.getHeadTutor())) {
    		throw new Exception("Head tutor assigned is not part of the organization");
    	}
    	course.setCreator(user);
    	Course savedCourse = courseRepository.save(course);
        if(organization != null) {
        	organization.addCourse(savedCourse);
        }
        return savedCourse;
    }

    @Override
    public Course addMemberToCourse(Long courseId, User user) throws Exception {
         Course c = courseRepository.findCourseById(courseId);
         if(c.getStudents().contains(user)) throw new Exception("You are already a course member");
         c.getStudents().add(user);
         return c;
    }

	@Override
	public void joinCourseAsStudent(Long organizationId, Long courseId, User user) throws Exception {
		Organization o = organizationService.getOrganizationById(organizationId);
		if(o == null) {
			throw new Exception("Organization does not exist");
		}
		if(!o.checkIfStudent(user)) throw new Exception("Student is not a part of a organization");
		Course c = courseRepository.findCourseById(courseId);
		c.addStudents(user);
	}
	
	@Override
	public void addSubjectsToCourse(Long organizationId, Long courseId, User user, Set<Subject> subjects) throws Exception {
		Organization organization = organizationService.getOrganizationById(organizationId);
		Course course = getCourseById(courseId);
		if(!userOrganizationRolesService.isUserPartOfOrganization(organization, user)) throw new Exception("You are not authorized to perform this task");
		if(!organizationService.isCoursePartOfOrganization(organization, course)) throw new Exception("Course and organization mismatch");
		if(!organizationService.isUserAdminOfOrganization(organization, user) && !isUserHeadTutor(course, user)) throw new Exception("You are not authorized to perform this task");
		Set<Subject> savedSubjects = new HashSet<>();
		subjects.forEach((subject) -> {
			savedSubjects.add(subjectService.createSubject(subject));
		});
		course.setSubjects(savedSubjects);
	}

	@Override
	public boolean isUserHeadTutor(Course course, User user) {
		return course.getHeadTutor().equals(user);
	}

	@Override
	public String addStudentsToCourse(Long organizationId, Long courseId, Set<Long> studentsId, User user) throws Exception {
		Organization organization = null;
    	if(organizationId != null) {
    		organization = organizationService.getOrganizationById(organizationId);
    	}
		Course course = getCourseById(courseId);
		if(!isUserHeadTutor(course, user)) {
			if(organization != null) {
	    		if(!organizationService.isUserAdminOfOrganization(organization, user)) {
	    			throw new Exception("You are not authorized to perform this task");
	    		}
	    	} else {
	    		throw new Exception("You are not authorized to perform this task");
	    	}
		}
		
		Integer studentsAdded = 0;
		Integer totalStudents = studentsId.size();
		
		if(organization == null) {
			Iterator<Long> itr = studentsId.iterator();
			while(itr.hasNext()) {
				User student = userService.getUserById(itr.next());
				if(student == null) continue;
				if(userService.isStudent(student)) {
					course.addStudents(student);
					studentsAdded++;
				}
			}
		} else {
			Iterator<Long> itr = studentsId.iterator();
			while(itr.hasNext()) {
				User student = userService.getUserById(itr.next());
				if(student == null) continue;
				if(userService.isStudent(student) && organization.checkIfStudent(student)) {
					course.addStudents(student);
					studentsAdded++;
				}
			}
		}
		
		return studentsAdded.toString() + " out of " + totalStudents.toString() + " students have been added to the course";
	}

	@Override
	public Set<UserDTO> getCourseNonAddedStudents(Long organizationId, Long courseId) throws Exception {
		Set<User> students = null;
		if(organizationId != null) {
			Organization organization = organizationService.getOrganizationById(organizationId);
			Course course = getCourseById(courseId);
			if(!organization.checkIfCourse(course)) {
				throw new Exception("Course does not belong to organization");
			}
			students = new HashSet<User>(organization.getStudents());
			students.removeAll(course.getStudents());
		}
		Set<UserDTO> studentsDTO = new HashSet<>();
		Iterator<User> itr = students.iterator();
		while(itr.hasNext()) {
			UserDTO student = new UserDTO();
			student.setUserPrimaryDetails(itr.next());
			studentsDTO.add(student);
		}
		return studentsDTO;
	}
}
