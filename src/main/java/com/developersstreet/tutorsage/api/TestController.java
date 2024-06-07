package com.developersstreet.tutorsage.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developersstreet.tutorsage.model.subject.Test;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.service.TestService;
import com.developersstreet.tutorsage.service.UtilityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

@RestController
@RequestMapping("/subject/{subjectId}/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

	private final UtilityService utilityService;
	private final TestService testService;
	
	@PostMapping("/create")
	public void createTest(@PathVariable Long subjectId, @RequestBody Test test, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("create test called");
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		try {
			User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
			testService.createTest(user, subjectId, test);
		} catch (Exception e) {
			utilityService.setExceptionResponse(e, response);
		}
	}
}