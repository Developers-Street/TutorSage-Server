package com.developersstreet.tutorsage.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developersstreet.tutorsage.model.organization.Organization;
import com.developersstreet.tutorsage.model.organization.UserOrganizationRoles;
import com.developersstreet.tutorsage.model.user.Role;
import com.developersstreet.tutorsage.model.user.User;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(Long Id);
    Set<Organization> findOrganizationsByNameContains(String query);
    Set<Organization> findOrganizationsByAdmin(User admin);
    Set<Organization> findOrganizationsByStudentsContains(User student);
    
    @Query("SELECT distinct o FROM Organization o, User u, UserOrganizationRoles uor where uor.user = u.id and uor.user = ?1 and o.id = uor.organization")
    Set<Organization> findMyOrganization(User user);
    
    @Query("SELECT distinct uor FROM User u, UserData ud, UserOrganizationRoles uor where uor.organization = ?1 and uor.role = ?2 and uor.user = u and u.userData = ud and ud.firstName like %?3%")
    Set<UserOrganizationRoles> findAllTeamByRole(Organization o, Role r, String query);
}