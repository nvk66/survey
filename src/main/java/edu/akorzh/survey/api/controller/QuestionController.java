package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.QuestionDto;
import edu.akorzh.survey.api.mapper.QuestionMapper;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/survey/{surveyId}/category/{categoryId}")
    public List<QuestionDto> get(@PathVariable(value = "surveyId") Long surveyId,
                                 @PathVariable(value = "categoryId") Long categoryId) {
        return questionService.get(surveyId, categoryId).stream().map(questionMapper::to).collect(Collectors.toList());
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "questionId") Long questionId) {
        questionService.delete(questionId);
    }


}
