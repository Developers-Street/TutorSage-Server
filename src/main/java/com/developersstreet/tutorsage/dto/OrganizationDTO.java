package com.developersstreet.tutorsage.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.developersstreet.tutorsage.enums.OrganizationType;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDTO {
	
	private long id;
	private String name;
	private String email;
	private String description;
	private String logoUrl;
	private OrganizationType type;
	private UserDTO creator;
	private UserDTO admin;
	private Set<UserDTO> students;
	private Set<CourseDTO> courses;
	private List<UserOrganizationRolesDTO> userOrganizationRoles;
	private boolean isJoinEnable;
	
	public void setOrganizationDetails(Organization organization) {
		this.id = organization.getId();
		this.name = organization.getName();
		this.email = organization.getEmail();
		this.description = organization.getDescription();
		this.logoUrl = organization.getLogoUrl();
		this.type = organization.getType();
		UserDTO creator = new UserDTO();
		creator.setUserCardInEntityDetails(organization.getCreator());
		this.creator = creator;
		UserDTO admin = new UserDTO();
		admin.setUserCardInEntityDetails(organization.getAdmin());
		this.admin = admin;
		setStudentsDetails(organization.getStudents());
		setCoursesDetails(organization.getCourses());
		this.isJoinEnable = true;
	}
	
	public void setUserOrganizationRoles(List<UserOrganizationRoles> uor) {
		if(userOrganizationRoles == null) userOrganizationRoles = new ArrayList<>();
		int l = uor.size();
		for(int i = 0; i < l; i++) {
			UserOrganizationRolesDTO userOrganizationRolesDTO = new UserOrganizationRolesDTO();
			UserOrganizationRoles u = uor.get(i);
			userOrganizationRolesDTO.setUsername(u.getUser().getUsername());
			userOrganizationRolesDTO.setProfile_pic_url(u.getUser().getUserData().getProfilePicUrl());
			userOrganizationRolesDTO.setUserId(u.getUser().getId());
			userOrganizationRolesDTO.setRole(u.getRole().getName());
			userOrganizationRoles.add(userOrganizationRolesDTO);
		}
	}
	
	public void setStudentsDetails(Set<User> students) {
		this.students = new HashSet<>();
		Iterator<User> itr = students.iterator();
		while (itr.hasNext()) {
			UserDTO student = new UserDTO();
			student.setUserCardInEntityDetails(itr.next());
			this.students.add(student);
        }
	}
	
	public void setCoursesDetails(Set<Course> courses) {
		this.courses = new HashSet<>();
		Iterator<Course> itr = courses.iterator();
		while(itr.hasNext()) {
			CourseDTO course = new CourseDTO();
			course.setCourseCardInEntityDetails(itr.next());
			this.courses.add(course);
		}
	}
}
