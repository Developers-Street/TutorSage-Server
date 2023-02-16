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
import com.developersstreet.tutorsage.repository.UserOrganizationRolesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationServiceImplementation implements OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    private final UserOrganizationRolesRepository userOrganizationRolesRepository;
    private final UserService userService;

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }
    
    @Override
    public OrganizationDTO getOrganizationDetails(Long id) throws Exception {
    	Organization organization = getOrganizationById(id);
    	if(organization == null) throw new Exception("Organization not found!!");
    	List<UserOrganizationRoles> userOrganizationRoles = userOrganizationRolesRepository.findByOrganization(organization);
    	OrganizationDTO organizationDTO = new OrganizationDTO();
    	organizationDTO.setOrganizationDetails(organization);
    	organizationDTO.setUserOrganizationRoles(userOrganizationRoles);
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
    	if(userService.isStudent(user.getId())) throw new Exception("Student cannot create organizations");
    	o.setCreator(user);
        o.setAdmin(user);
        return organizationRepository.save(o);
    }

	@Override
	public void joinOrganizationAsStudent(Long id, User user) {
		Organization o = organizationRepository.findOrganizationById(id);
		o.addStudent(user);
		organizationRepository.save(o);
	}

	@Override
	public void joinOrganization(Long id, User user, Role role) {
		Organization o = organizationRepository.findOrganizationById(id);
		UserOrganizationRoles userOrganizationRoles = new UserOrganizationRoles(o, role, user);
		userOrganizationRolesRepository.save(userOrganizationRoles);
	}

	@Override
	public Set<Organization> getMyOrganization(User user) {
		boolean isStudent = userService.isStudent(user.getId());
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
	public boolean isUserPartOfOrganization(Organization organization, User user) {
		if(organization.getAdmin().equals(user)) return true;
		List<UserOrganizationRoles> userOrganizationRoles = userOrganizationRolesRepository.findByUserAndOrganization(user, organization);
		if(userOrganizationRoles.size() > 0) return true;
		return false;
	}

	@Override
	public boolean isUserAdminOfOrganization(Organization organization, User user) {
		return organization.getAdmin().equals(user);
	}

	@Override
	public boolean isCoursePartOfOrganization(Organization organization, Course course) {
		return organization.checkIfCourse(course);
	}
}
