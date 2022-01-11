package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.model.Survey;

import java.util.List;

public interface SurveyService {

    Survey add(Survey survey, String userName);

    Survey get(Long surveyId);

    List<Question> result(Long surveyId);
}
