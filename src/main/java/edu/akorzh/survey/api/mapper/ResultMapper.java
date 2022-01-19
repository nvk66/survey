package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.ResultDto;
import edu.akorzh.survey.common.AnswerType;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Question;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    public List<ResultDto> to(List<Answer> answers) {
        if (answers.isEmpty()) {
            throw new NotFoundException();
        }

        return answers
                .stream()
                .map(Answer::getQuestion)
                .map(question -> {
                    ResultDto resultDto = ResultDto.builder()
                            .questionId(question.getId())
                            .questionName(question.getName())
                            .categoryId(question.getCategory().getId())
                            .categoryName(question.getCategory().getName())
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
                    return resultDto;
                })
                .collect(Collectors.toList());

//        ResultDto resultDto = ResultDto.builder()
//                .questionId(question.getId())
//                .questionName(question.getName())
//                .categoryId(question.getCategory().getId())
//                .build();
//        if (question.getType() == AnswerType.FREE_FORM) {
//            resultDto.setAnswers(question.getAnswers()
//                    .stream()
//                    .map(Answer::getText)
//                    .collect(Collectors.toList()));
//        } else {
//            resultDto.setAverage(BigDecimal.valueOf(question.getAnswers()
//                    .stream()
//                    .map(Answer::getValue)
//                    .mapToDouble(value -> value)
//                    .average()
//                    .orElse(0)));
//        }
//        return resultDto;

//        return ResultDto
//                .builder()
//                .average(BigDecimal.valueOf(question.getAnswers()
//                        .stream()
//                        .map(Answer::getValue)
//                        .mapToDouble(value -> value)
//                        .average()
//                        .orElse(0)))
//                .questionName(question.getName())
//                .categoryId(question.getCategory().getId())
//                .build();
    }

}
