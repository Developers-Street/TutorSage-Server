package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;

public interface SubjectService {
	Subject createSubject(Subject subject);
	Subject getSubjectById(Long id);
	boolean isUserSubjectTutor(Subject subject, User user);
}
