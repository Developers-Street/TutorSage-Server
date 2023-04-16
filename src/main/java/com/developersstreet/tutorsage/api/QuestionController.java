package com.developersstreet.tutorsage.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developersstreet.tutorsage.model.subject.MCQ;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.service.QuestionService;
import com.developersstreet.tutorsage.service.UtilityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
	
	private final UtilityService utilityService;
	private final QuestionService questionService;
	
	@PostMapping("/mcq/create")
	public void createMCQQuestion(@RequestBody MCQ mcq, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		try {
			User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
			questionService.createMcqQuestion(user, mcq);
		} catch (Exception exception) {
			utilityService.setExceptionResponse(exception, response);
		}
	}
}
