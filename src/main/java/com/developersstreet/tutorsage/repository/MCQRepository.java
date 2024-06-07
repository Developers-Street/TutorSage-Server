package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.subject.MCQ;

public interface MCQRepository extends JpaRepository<MCQ, Long> {

}
