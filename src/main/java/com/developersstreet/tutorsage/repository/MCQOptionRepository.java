package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.MCQOption;

public interface MCQOptionRepository extends JpaRepository<MCQOption, Long> {
	
}
