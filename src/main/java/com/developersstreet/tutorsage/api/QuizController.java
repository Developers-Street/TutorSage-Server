package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.Quiz;
import com.developersstreet.tutorsage.service.ClassService;
import com.developersstreet.tutorsage.service.QuizService;
import com.developersstreet.tutorsage.service.UtilityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private final UtilityService utilityService;
    private final ClassService classService;

    @PostMapping("/create")
    public void createQuiz(@Valid @RequestBody CreateQuizForm createQuizForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Quiz quiz = quizService.createQuiz(createQuizForm.getQuiz());
            classService.addQuizToClass(createQuizForm.getClassId(), quiz);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}

@Data
class CreateQuizForm {
    private Quiz quiz;
    private Long classId;
}