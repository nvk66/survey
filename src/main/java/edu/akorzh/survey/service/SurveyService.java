package edu.akorzh.survey.service;

import edu.akorzh.survey.common.SurveyUserStatus;
import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Permission;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.model.Survey;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SurveyService {

    Survey add(Survey survey, String userName);

    Survey get(Long surveyId);

    List<Permission> get(SurveyUserStatus status, Authentication authentication);

    List<Permission> getForTeacher(Authentication authentication);

    List<Answer> result(Long permissionId, Authentication authentication);
}
