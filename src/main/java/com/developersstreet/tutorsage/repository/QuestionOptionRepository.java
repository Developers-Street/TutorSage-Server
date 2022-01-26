package com.developersstreet.tutorsage.repository;

import com.developersstreet.tutorsage.model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    QuestionOption findQuestionOptionById(Long Id);
}
