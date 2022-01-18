package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.common.SurveyUserStatus;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.*;
import edu.akorzh.survey.repository.*;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final UserRepository userRepository;
    private final PupilRepository pupilRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final PermissionRepository permissionRepository;

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
    public List<Permission> get(SurveyUserStatus status, Authentication authentication) {
        User user = userRepository.findUserByLogin(authentication.getName()).orElseThrow(NotFoundException::new);

        Pupil pupil = pupilRepository.findByUser(user);
        if (pupil == null) {
            throw new NotFoundException();
        }

        return permissionRepository.getPermissions(pupil.getGroup());

//        return surveyRepository.getSurveys(pupil.getGroup());
    }

    @Override
    public List<Question> result(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
        return questionRepository.findAllBySurvey(survey);
    }
}
