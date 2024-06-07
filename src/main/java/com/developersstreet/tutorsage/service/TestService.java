package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.subject.Test;
import com.developersstreet.tutorsage.model.user.User;

public interface TestService {
	Test createTest(User user, Long subjectId, Test test) throws Exception;
}
