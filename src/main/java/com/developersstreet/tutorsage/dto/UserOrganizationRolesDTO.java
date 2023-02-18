package com.developersstreet.tutorsage.dto;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrganizationRolesDTO {
	private String username;
	private String role;
	private Long userId;
	private String profile_pic_url;
	
	public void setPrimaryDetails(User user, Role role) {
		this.username = user.getUsername();
		if(role != null) {
			this.role = role.getName();
		}
		this.userId = user.getId();
		this.profile_pic_url = user.getUserData().getProfilePicUrl();
	}
}
