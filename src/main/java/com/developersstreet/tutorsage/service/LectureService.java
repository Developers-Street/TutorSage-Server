package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Lecture;
import com.developersstreet.tutorsage.model.User;

public interface LectureService {
	Lecture addLectureToSubject(Lecture lecture, Long organizationId, Long courseId, Long subjectId, User user);
}
