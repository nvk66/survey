package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.ResultDto;
import edu.akorzh.survey.api.dto.SurveyDto;
import edu.akorzh.survey.api.dto.SurveyInfoDto;
import edu.akorzh.survey.api.mapper.ResultMapper;
import edu.akorzh.survey.api.mapper.SurveyInfoDtoMapper;
import edu.akorzh.survey.api.mapper.SurveyMapper;
import edu.akorzh.survey.common.SurveyUserStatus;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyMapper surveyMapper;

    private final SurveyService surveyService;

    private final ResultMapper resultMapper;

    private final SurveyInfoDtoMapper surveyInfoDtoMapper;

    @PostMapping("/")
    @RolesAllowed("ROLE_USER")
    public SurveyDto add(@Validated @RequestBody SurveyDto surveyDto,
                         Authentication authentication) {
        final Survey survey = surveyService.add(surveyMapper.to(surveyDto), authentication.getName());
        return surveyMapper.to(survey);
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_USER")
    public SurveyDto get(@PathVariable(value = "id") Long surveyId) {
        return surveyMapper.to(surveyService.get(surveyId));
    }

    @GetMapping("/{status}")
    @RolesAllowed("ROLE_USER")
    public List<SurveyInfoDto> get(@PathVariable(value = "status") SurveyUserStatus status,
                                   Authentication authentication) {
        return surveyService.get(status, authentication)
                .stream()
                .map(surveyInfoDtoMapper::to)
                .collect(Collectors.toList());
    }

    @GetMapping("/result/{id}")
    @RolesAllowed("ROLE_UNIVERSITY_ADMINISTRATOR")
    public List<ResultDto> getResult(@PathVariable(value = "id") Long surveyId) {
        return surveyService.result(surveyId).stream().map(resultMapper::to).collect(Collectors.toList());
    }
}
