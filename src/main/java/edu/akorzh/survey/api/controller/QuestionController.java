package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.QuestionDto;
import edu.akorzh.survey.api.mapper.QuestionMapper;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping("/survey/{surveyId}/category/{categoryId}")
    public QuestionDto add(@PathVariable(value = "surveyId") Long surveyId,
                           @PathVariable(value = "categoryId") Long categoryId,
                           @Validated @RequestBody QuestionDto questionDto) {
        final Question question = questionService.add(questionMapper.to(questionDto), surveyId, categoryId);
        return questionMapper.to(question);
    }


}
