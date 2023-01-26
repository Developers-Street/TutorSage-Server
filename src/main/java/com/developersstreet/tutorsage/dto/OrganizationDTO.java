package com.developersstreet.tutorsage.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.developersstreet.tutorsage.enums.OrganizationType;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
	
	private long id;
	private String name;
	private String email;
	private String description;
	private String logoUrl;
	private OrganizationType type;
	private User creator;
	private User admin;
	private Set<User> students;
	private Set<Course> courses;
	private List<UserOrganizationRolesDTO> userOrganizationRoles;
	
	public void setOrganizationDetails(Organization organization) {
		id = organization.getId();
		name = organization.getName();
		email = organization.getEmail();
		description = organization.getDescription();
		logoUrl = organization.getLogoUrl();
		type = organization.getType();
		creator = organization.getCreator();
		admin = organization.getAdmin();
		students = organization.getStudents();
		courses = organization.getCourses();
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
}
