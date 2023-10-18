package com.CourseWork.skyprocourseWork2.Service;

import com.CourseWork.skyprocourseWork2.dto.Question;
import com.CourseWork.skyprocourseWork2.exception.NotEnoughQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    QuestionService questionService;

    @InjectMocks
    ExaminerServiceImpl underTest;

    @Test
    void getQuestions_shouldThrowExceptionWhenEnoughQuestions() {
        when(questionService.getAll()).thenReturn(Collections.emptySet());

        assertThrows(NotEnoughQuestion.class,
             () -> underTest.getQuestions(1));
    }

    @Test
    void getQuestions_shouldReturnUniqueQuestionsCollection() {
        int amount = 2;
        Question question = new Question("question", "answer");
        Question question1 = new Question("question1", "answer1");
        Set<Question> questions = Set.of(question, question1);
        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(question, question1);

        Collection<Question> result = underTest.getQuestions(amount);

        assertEquals(questions, result);
        assertEquals(amount, result.stream().distinct().count() );
    }
}