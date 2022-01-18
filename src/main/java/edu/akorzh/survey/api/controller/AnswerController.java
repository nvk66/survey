package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.AnswerDto;
import edu.akorzh.survey.api.mapper.AnswerMapper;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.service.AnswerService;
import edu.akorzh.survey.service.PermissionService;
import edu.akorzh.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerMapper answerMapper;
    private final PermissionService permissionService;
    private final AnswerService answerService;
    private final SurveyService surveyService;

    @PostMapping("/survey/{surveyId}/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed("ROLE_USER")
    public void submitAnswer(@PathVariable(value = "surveyId") Long surveyId,
                             @PathVariable(value = "permissionId") Long permissionId,
                             @Validated @RequestBody List<AnswerDto> answerDto) {
        surveyService.get(surveyId);
        answerService.add(answerMapper.to(answerDto, permissionService.get(permissionId)));
    }

}
