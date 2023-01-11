package com.developersstreet.tutorsage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }

    @Override
    public List<Organization> getOrganizationsByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception {
        List<Organization> organizations = organizationRepository.findOrganizationsByNameContains(query);
        if(organizations.size() == 0) throw new Exception("No Organizations found");
        Long fromIndex = limit * (offset - 1);
        Long toIndex = limit * (offset - 1) + limit;
        if(organizations.size() < toIndex) organizations = organizations.subList(Integer.parseInt(fromIndex.toString()), organizations.size());
        else organizations = organizations.subList(Integer.parseInt(fromIndex.toString()), Integer.parseInt(toIndex.toString()));
        return organizations;
    }

    @Override
    public Organization createOrganization(Organization o) {
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
}
