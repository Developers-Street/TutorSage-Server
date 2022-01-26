package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
//    Question findQuestionById(Long Id);
}
