package com.developersstreet.tutorsage.dto;

import java.util.Set;

import com.developersstreet.tutorsage.enums.CourseVisibilityType;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Subject;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
	private Long id;
	private String name;
	private UserDTO creator;
	private UserDTO headTutor;
	private CourseVisibilityType visibility;
	private Set<Subject> subjects;
	private Set<UserDTO> students;
	
	public void setCourseCardInEntityDetails(Course course) {
		this.id = course.getId();
		this.name = course.getName();
		UserDTO headTutor = new UserDTO();
		headTutor.setUserCardInEntityDetails(course.getHeadTutor());
		this.headTutor = headTutor;
	}
}
