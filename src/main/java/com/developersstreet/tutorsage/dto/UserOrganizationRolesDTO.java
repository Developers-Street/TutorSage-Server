package com.developersstreet.tutorsage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrganizationRolesDTO {
	private String username;
	private String role;
	private Long userId;
	private String profile_pic_url;
}
