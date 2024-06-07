package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.subject.MCQ;
import com.developersstreet.tutorsage.model.user.User;

public interface QuestionService {
	MCQ createMcqQuestion(User user, MCQ mcq) throws Exception;
}
