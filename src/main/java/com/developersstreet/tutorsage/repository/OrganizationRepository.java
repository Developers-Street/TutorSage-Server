package com.developersstreet.tutorsage.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.User;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(Long Id);
    List<Organization> findOrganizationsByNameContains(String query);
    List<Organization> findOrganizationsByAdmin(User admin);
    List<Organization> findOrganizationsByStudentsContains(User student);
    
    @Query("SELECT distinct o FROM Organization o, User u, UserOrganizationRoles uor where uor.user = u.id and uor.user = ?1 and o.id = uor.organization")
    Set<Organization> findMyOrganization(User user);
}
