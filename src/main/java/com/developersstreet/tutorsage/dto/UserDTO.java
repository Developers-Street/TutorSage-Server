package com.developersstreet.tutorsage.dto;

import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	private Long id;
	private String email;
	private String username;
	private UserDataDTO userData;
	
	public void setUserCardInEntityDetails(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.userData = new UserDataDTO();
		this.userData.setProfilePicUrl(user.getUserData().getProfilePicUrl());
	}
	
	public void setUserDetailsFromUserOrganizationRole(UserOrganizationRoles userOrganizationRoles) {
		User user = userOrganizationRoles.getUser();
		this.id = user.getId();
		this.userData = new UserDataDTO();
		this.userData.setFirstName(user.getUserData().getFirstName());
		this.userData.setMiddleName(user.getUserData().getMiddleName());
		this.userData.setLastName(user.getUserData().getLastName());
		this.username = user.getUsername();
	}
	
	public void setUserPrimaryDetails(User user) {
		this.id = user.getId();
		this.userData = new UserDataDTO();
		this.userData.setFirstName(user.getUserData().getFirstName());
		this.userData.setMiddleName(user.getUserData().getMiddleName());
		this.userData.setLastName(user.getUserData().getLastName());
		this.username = user.getUsername();
	}
}
