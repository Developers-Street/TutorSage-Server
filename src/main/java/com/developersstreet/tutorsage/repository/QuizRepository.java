package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findQuizById(Long Id);
}
