package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.repository.SurveyRepository;
import edu.akorzh.survey.repository.UserRepository;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    @Override
    @Transactional
    public Survey add(Survey survey, String userName) {
        User user = userRepository.findUserByLogin(userName).orElseThrow(NotFoundException::new);

        survey.setUser(user);

        surveyRepository.save(survey);

        return survey;
    }

    @Override
    @Transactional
    public Survey get(Long surveyId) {
        return surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
    }
}
