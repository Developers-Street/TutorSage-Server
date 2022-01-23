package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    Class findClassById(Long Id);
    List<Class> findClassesByNameContains(String query);
}
