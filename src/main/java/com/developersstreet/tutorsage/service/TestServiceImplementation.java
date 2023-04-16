package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.enums.TestStatus;
import com.developersstreet.tutorsage.model.subject.Test;
import com.developersstreet.tutorsage.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TestServiceImplementation implements TestService{
	
	private final TestRepository testRepository;
	
	@Override
	public Test createTest() {
		Test newTest = new Test();
		newTest.setStatus(TestStatus.onhold);
		Test savedNewTest = testRepository.save(newTest);
		return savedNewTest;
	}
}