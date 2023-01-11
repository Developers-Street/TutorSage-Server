package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.developersstreet.tutorsage.model.UserOrganizationRoles;

public interface UserOrganizationRolesRepository extends JpaRepository<UserOrganizationRoles, Long> {
	
}
