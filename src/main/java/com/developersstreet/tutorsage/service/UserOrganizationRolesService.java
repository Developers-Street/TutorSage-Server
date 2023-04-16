package com.developersstreet.tutorsage.service;

import java.util.List;

import com.developersstreet.tutorsage.model.organization.Organization;
import com.developersstreet.tutorsage.model.organization.UserOrganizationRoles;
import com.developersstreet.tutorsage.model.user.Role;
import com.developersstreet.tutorsage.model.user.User;

public interface UserOrganizationRolesService {
	UserOrganizationRoles createUserOrganizationRoles(Organization organization, Role role, User user) throws Exception;
	List<UserOrganizationRoles> getUserOrganizationRolesByOrganization(Organization organization);
	boolean isUserPartOfOrganization(Organization organization, User user);
}
