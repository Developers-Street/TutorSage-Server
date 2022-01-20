package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
    Class findClassById(Long Id);
}
