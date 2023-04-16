package com.developersstreet.tutorsage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developersstreet.tutorsage.model.subject.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
	Subject findSubjectById(Long id);
}
