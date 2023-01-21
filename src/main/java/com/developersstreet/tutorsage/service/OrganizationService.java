package com.developersstreet.tutorsage.service;

import java.util.List;
import java.util.Set;

import com.developersstreet.tutorsage.model.Course;
import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;

public interface OrganizationService {
    Organization createOrganization(Organization o);
    Organization getOrganizationById(Long id);
    void joinOrganizationAsStudent(Long id, User user);
    void joinOrganization(Long id, User user, Role role);
    Set<Organization> getMyOrganization(User user);
    List<Organization> getMyOrganizationAsStudent(User user);
    List<Organization> getOrganizationsByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception;
    Set<Course> getCoursesByOrganizationId(Long organizationId, String query, Long offset, Long limit) throws Exception;
    boolean isUserPartOfOrganization(Organization organization, User user);
    boolean isUserAdminOfOrganization(Organization organization, User user);
    boolean isCoursePartOfOrganization(Organization organization, Course course);
}
