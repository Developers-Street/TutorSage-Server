package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.subject.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}