package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Question;

import java.util.List;

public interface QuestionService {

    Question add(Question question, Long surveyId, Long categoryId);

    List<Question> get(Long surveyId, Long categoryId);

    void delete(Long questionId);
}
