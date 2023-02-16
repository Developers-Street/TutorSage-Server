package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.subject.Lecture;

public interface SubjectService {
	Lecture addLectureToSubject(Lecture lecture, Long subjectId, User user);
	Subject getSubjectById(Long id);
}
