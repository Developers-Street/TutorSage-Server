package com.developersstreet.tutorsage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;
import com.developersstreet.tutorsage.repository.RoleRepository;
import com.developersstreet.tutorsage.repository.UserOrganizationRolesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserOrganizationRolesServiceImplementation implements UserOrganizationRolesService {
	
	private final UserOrganizationRolesRepository userOrganizationRolesRepository;

	@Override
	public UserOrganizationRoles createUserOrganizationRoles(Organization organization, Role role, User user) throws Exception {
		if(userOrganizationRolesRepository.findByUserAndOrganizationAndRole(user, organization, role).size() > 0) {
			throw new Exception("User already have this role in the organization");
		}
		UserOrganizationRoles userOrganizationRoles = new UserOrganizationRoles(organization, role, user);
		return userOrganizationRolesRepository.save(userOrganizationRoles);
	}
	
	@Override
	public List<UserOrganizationRoles> getUserOrganizationRolesByOrganization(Organization organization) {
		return userOrganizationRolesRepository.findByOrganization(organization);	
	}

	@Override
	public boolean isUserPartOfOrganization(Organization organization, User user) {
		if(organization.getAdmin().equals(user)) return true;
		List<UserOrganizationRoles> userOrganizationRoles = userOrganizationRolesRepository.findByUserAndOrganization(user, organization);
		if(userOrganizationRoles.size() > 0) return true;
		return false;
	}
}
