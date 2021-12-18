package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.SurveyDto;
import edu.akorzh.survey.api.mapper.SurveyMapper;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyMapper surveyMapper;

    private final SurveyService surveyService;

    @PostMapping("/")
    public SurveyDto add(@Validated @RequestBody SurveyDto surveyDto,
                         Authentication authentication) {
        final Survey survey = surveyService.add(surveyMapper.to(surveyDto), authentication.getName());
        return surveyMapper.to(survey);
    }

}
