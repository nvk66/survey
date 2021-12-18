package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Question;

public interface QuestionService {

    Question add(Question question, Long surveyId, Long categoryId);
}
