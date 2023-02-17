package com.developersstreet.tutorsage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.dto.OrganizationDTO;
import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;
import com.developersstreet.tutorsage.repository.OrganizationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationServiceImplementation implements OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    
    private final UserService userService;
    private final UserOrganizationRolesService userOrganizationRolesService;

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }
    
    @Override
    public OrganizationDTO getOrganizationDetails(Long id, User user) throws Exception {
    	Organization organization = getOrganizationById(id);
    	if(organization == null) throw new Exception("Organization not found!!");
    	List<UserOrganizationRoles> userOrganizationRoles = userOrganizationRolesService.getUserOrganizationRolesByOrganization(organization);
    	OrganizationDTO organizationDTO = new OrganizationDTO();
    	organizationDTO.setOrganizationDetails(organization);
    	organizationDTO.setUserOrganizationRoles(userOrganizationRoles);
    	if(isUserPartOfOrganization(organization, user)) organizationDTO.setJoinEnable(false);
    	return organizationDTO;
    }

    @Override
    public List<Organization> getOrganizationsByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception {
        Set<Organization> organizations = organizationRepository.findOrganizationsByNameContains(query);
        if(organizations.size() == 0) throw new Exception("No Organizations found");
        Long fromIndex = limit * (offset - 1);
        Long toIndex = limit * (offset - 1) + limit;
        List<Organization> organizationsList = new ArrayList<>(organizations);
        if(organizations.size() < toIndex) organizationsList = organizationsList.subList(Integer.parseInt(fromIndex.toString()), organizationsList.size());
        else organizationsList = organizationsList.subList(Integer.parseInt(fromIndex.toString()), Integer.parseInt(toIndex.toString()));
        return organizationsList;
    }

    @Override
    public Organization createOrganization(Organization o, User user) throws Exception {
    	if(userService.isStudent(user)) throw new Exception("Student cannot create organizations");
    	o.setCreator(user);
        o.setAdmin(user);
        return organizationRepository.save(o);
    }

	@Override
	public void joinOrganization(Long id, User user, Long roleId) throws Exception {
		Role role = userService.getRoleById(roleId);
		Organization o = organizationRepository.findOrganizationById(id);
		if(role.getName().equals("ROLE_STUDENT")) {
			if(!userService.isStudent(user)) {
				throw new Exception("User is not a student. Cannot join as student");
			}
			o.addStudent(user);
			organizationRepository.save(o);
		} else {
			if(userService.isStudent(user)) {
				throw new Exception("Student cannot join organization with different role");
			}
			if(o.getAdmin().equals(user)) {
				throw new Exception("User is admin of the organization. Cannot join");
			}
			userOrganizationRolesService.createUserOrganizationRoles(o, role, user);
		}
	}

	@Override
	public Set<Organization> getMyOrganization(User user) {
		boolean isStudent = userService.isStudent(user);
		Set<Organization> organizations = new HashSet<>();
		if(isStudent) {
			organizations = organizationRepository.findOrganizationsByStudentsContains(user);
		}
		else {
			organizations = organizationRepository.findOrganizationsByAdmin(user);
			Set<Organization> myOrganizations = organizationRepository.findMyOrganization(user);
			organizations.addAll(myOrganizations);	
		}
		return organizations;
	}

	@Override
	public boolean isUserAdminOfOrganization(Organization organization, User user) {
		return organization.getAdmin().equals(user);
	}

	@Override
	public boolean isCoursePartOfOrganization(Organization organization, Course course) {
		return organization.checkIfCourse(course);
	}

	@Override
	public boolean isUserPartOfOrganization(Organization organization, User user) {
		if(userOrganizationRolesService.isUserPartOfOrganization(organization, user)) {
			return true;
		}
		if(organization.checkIfStudent(user)) return true;
		return false;
	}
}
