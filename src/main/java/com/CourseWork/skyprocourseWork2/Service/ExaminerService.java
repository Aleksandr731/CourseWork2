package com.CourseWork.skyprocourseWork2.Service;

import com.CourseWork.skyprocourseWork2.dto.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
