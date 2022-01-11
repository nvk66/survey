package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.repository.QuestionRepository;
import edu.akorzh.survey.repository.SurveyRepository;
import edu.akorzh.survey.repository.UserRepository;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Survey add(Survey survey, String userName) {
        log.info("!!! {}", survey);

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

    @Override
    public List<Question> result(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
        return questionRepository.findAllBySurvey(survey);
    }
}
