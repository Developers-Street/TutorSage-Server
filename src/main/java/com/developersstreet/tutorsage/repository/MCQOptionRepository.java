package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.subject.MCQOption;

public interface MCQOptionRepository extends JpaRepository<MCQOption, Long> {
	
}
