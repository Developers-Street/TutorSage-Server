package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.enums.CourseVisibilityType;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.repository.CourseRepository;
import com.developersstreet.tutorsage.repository.OrganizationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationService organizationService;

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
    public Course createCourse(Course c, User user, Long organizationId) {
    	c.setCreator(user);
    	c.setHeadTutor(user);
        Course savedCourse = courseRepository.save(c);
        if(organizationId != null) {
        	Organization o = organizationRepository.findOrganizationById(organizationId);
        	o.addCourses(savedCourse);
        	organizationRepository.save(o);
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
		Organization o = organizationRepository.findOrganizationById(organizationId);
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
		if(!organizationService.isUserPartOfOrganization(organization, user)) throw new Exception("You are not authorized to perform this task");
		if(!organizationService.isCoursePartOfOrganization(organization, course)) throw new Exception("Course and organization mismatch");
		if(!organizationService.isUserAdminOfOrganization(organization, user) && !isUserHeadTutor(course, user)) throw new Exception("You are not authorized to perform this task");
		course.setSubjects(subjects);
	}

	@Override
	public boolean isUserHeadTutor(Course course, User user) {
		return course.getHeadTutor().equals(user);
	}
}
