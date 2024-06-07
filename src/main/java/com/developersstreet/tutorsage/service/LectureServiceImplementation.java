package com.developersstreet.tutorsage.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.developersstreet.tutorsage.model.organization.Course;
import com.developersstreet.tutorsage.model.subject.Lecture;
import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;
import com.developersstreet.tutorsage.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureServiceImplementation implements LectureService {
	
	private final LectureRepository lectureRepository;
	
	private final SubjectService subjectService;
	private final CourseService courseService;
	
	@Override
	public Lecture addLectureToSubject(Lecture lecture, Long courseId, Long subjectId, User user) throws Exception  {
		Course course = courseService.getCourseById(courseId);
		Subject subject = subjectService.getSubjectById(subjectId);
		if(!courseService.isUserHeadTutor(course, user) && !subjectService.isUserSubjectTutor(subject, user)) {
			throw new Exception("You are not authorized to perform this task");
		}
		Lecture savedLecture = lectureRepository.save(lecture);
		subject.addLecture(savedLecture);
		return savedLecture;
	}
}