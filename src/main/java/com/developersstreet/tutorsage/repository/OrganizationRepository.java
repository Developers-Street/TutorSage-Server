package com.developersstreet.tutorsage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(Long Id);
    List<Organization> findOrganizationsByNameContains(String query);
}
