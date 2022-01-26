package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Quiz;
import com.developersstreet.tutorsage.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImplementation implements QuizService{
    private final QuizRepository quizRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
