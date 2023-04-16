package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImplementation implements SubjectService {
	
	private final SubjectRepository subjectRepository;

	@Override
	public Subject createSubject(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	@Override
	public Subject getSubjectById(Long id) {
		return subjectRepository.findSubjectById(id);
	}

	@Override
	public boolean isUserSubjectTutor(Subject subject, User user) {
		if(subject.getTutor().equals(user)) return true;
		return false;
	}

}
