package com.developersstreet.tutorsage.service;

import java.util.List;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;

public interface OrganizationService {
    Organization createOrganization(Organization o);
    Organization getOrganizationById(Long id);
    void joinOrganizationAsStudent(Long id, User user);
    void joinOrganization(Long id, User user, Role role);
    List<Organization> getOrganizationsByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
}
