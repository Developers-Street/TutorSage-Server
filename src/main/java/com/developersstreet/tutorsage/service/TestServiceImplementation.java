package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.enums.TestStatus;
import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.subject.Test;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TestServiceImplementation implements TestService{
	
	private final TestRepository testRepository;
	private final SubjectService subjectService;
	
	@Override
	public Test createTest(User user, Long subjectId, Test test) throws Exception {
		Subject subject = subjectService.getSubjectById(subjectId);
		
		if(subject == null) throw new Exception("Subject does not exist");
		
		if(!subjectService.isUserSubjectTutor(subject, user)) throw new Exception("You are not authorized to perform this task");
		
		test.setStatus(TestStatus.onhold);
		Test savedNewTest = testRepository.save(test);
		
		subject.addTest(savedNewTest);
		return savedNewTest;
	}
}