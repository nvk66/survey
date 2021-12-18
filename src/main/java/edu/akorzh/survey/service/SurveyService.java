package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Survey;

public interface SurveyService {

    Survey add(Survey survey, String userName);

    Survey get(Long surveyId);
}
