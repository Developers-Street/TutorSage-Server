package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	
}
