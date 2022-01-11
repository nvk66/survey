package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.ResultDto;
import edu.akorzh.survey.common.AnswerType;
import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Question;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    public ResultDto to(Question question) {
        ResultDto resultDto = ResultDto.builder()
                .questionId(question.getId())
                .questionName(question.getName())
                .categoryId(question.getCategory().getId())
                .build();
        if (question.getType() == AnswerType.FREE_FORM) {
            resultDto.setAnswers(question.getAnswers()
                    .stream()
                    .map(Answer::getText)
                    .collect(Collectors.toList()));
        } else {
            resultDto.setAverage(BigDecimal.valueOf(question.getAnswers()
                    .stream()
                    .map(Answer::getValue)
                    .mapToDouble(value -> value)
                    .average()
                    .orElse(0)));
        }

        return ResultDto
                .builder()
                .average(BigDecimal.valueOf(question.getAnswers()
                        .stream()
                        .map(Answer::getValue)
                        .mapToDouble(value -> value)
                        .average()
                        .orElse(0)))
                .questionName(question.getName())
                .categoryId(question.getCategory().getId())
                .build();
    }

}
