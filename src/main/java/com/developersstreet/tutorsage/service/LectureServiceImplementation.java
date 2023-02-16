package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.Lecture;
import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureServiceImplementation implements LectureService {
	
	private final LectureRepository lectureRepository;
	private final SubjectService subjectService;
	
	@Override
	public Lecture addLectureToSubject(Lecture lecture, Long organizationId, Long courseId, Long subjectId, User user) {
		Lecture savedLecture = lectureRepository.save(lecture);
		Subject subject = subjectService.getSubjectById(subjectId);
		subject.addLecture(savedLecture);
		return savedLecture;
	}
}
