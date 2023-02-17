package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;

public interface SubjectService {
	Subject createSubject(Subject subject);
	Subject getSubjectById(Long id);
	boolean isUserSubjectTutor(Subject subject, User user);
}
