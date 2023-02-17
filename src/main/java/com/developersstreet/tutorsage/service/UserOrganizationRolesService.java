package com.developersstreet.tutorsage.service;

import java.util.List;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;

public interface UserOrganizationRolesService {
	UserOrganizationRoles createUserOrganizationRoles(Organization organization, Role role, User user);
	List<UserOrganizationRoles> getUserOrganizationRolesByOrganization(Organization organization);
	boolean isUserPartOfOrganization(Organization organization, User user);
}
