package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.subject.MCQ;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.repository.MCQRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImplementation implements QuestionService {
	
	private final UserService userService;
	private final MCQRepository mcqRepository;

	@Override
	public MCQ createMcqQuestion(User user, MCQ mcq) throws Exception {
		if(userService.isStudent(user)) throw new Exception("Students cannot create questions");
		mcq.setAuthor(user);
		return mcqRepository.save(mcq);
	}

}
