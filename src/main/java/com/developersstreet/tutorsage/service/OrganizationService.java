package com.developersstreet.tutorsage.service;

import java.util.List;

import com.developersstreet.tutorsage.model.Organization;

public interface OrganizationService {
    Organization createOrganization(Organization o);
    Organization getOrganizationById(Long id);
    List<Organization> getOrganizationsByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
}
