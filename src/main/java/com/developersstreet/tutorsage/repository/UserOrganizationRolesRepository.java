package com.developersstreet.tutorsage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;

public interface UserOrganizationRolesRepository extends JpaRepository<UserOrganizationRoles, Long> {
	List<UserOrganizationRoles> findByUserAndOrganization(User user, Organization organization);
	List<UserOrganizationRoles> findByOrganization(Organization organization);
	List<UserOrganizationRoles> findByUserAndOrganizationAndRole(User user, Organization organization, Role role);
	List<UserOrganizationRoles> findByOrganizationAndRole(Organization organization, Role role);
}
