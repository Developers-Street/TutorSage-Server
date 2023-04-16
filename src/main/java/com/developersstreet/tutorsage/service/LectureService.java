package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.subject.Lecture;
import com.developersstreet.tutorsage.model.user.User;

public interface LectureService {
	Lecture addLectureToSubject(Lecture lecture, Long courseId, Long subjectId, User user) throws Exception;
}
