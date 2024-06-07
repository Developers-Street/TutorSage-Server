package com.developersstreet.tutorsage.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developersstreet.tutorsage.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name IN :roles")
    Set<Role> findRolesByNames(@Param("roles") Set<String> roles);
}