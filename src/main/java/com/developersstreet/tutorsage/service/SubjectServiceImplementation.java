package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.Subject;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.subject.Lecture;
import com.developersstreet.tutorsage.repository.LectureRepository;
import com.developersstreet.tutorsage.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImplementation implements SubjectService {
	
	private final LectureRepository lectureRepository;
	private final SubjectRepository subjectRepository;
	
	@Override
	public Subject getSubjectById(Long id) {
		return subjectRepository.findSubjectById(id);
	}
	
	@Override
	public Lecture addLectureToSubject(Lecture lecture, Long subjectId, User user) {
		Lecture savedLecture = lectureRepository.save(lecture);
		Subject subject = getSubjectById(subjectId);
		subject.addLecture(savedLecture);
		return savedLecture;
	}
}
