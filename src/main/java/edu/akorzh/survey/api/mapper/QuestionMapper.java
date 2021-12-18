package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.QuestionDto;
import edu.akorzh.survey.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public QuestionDto to(Question question) {
        return QuestionDto.builder()
                .type(question.getType())
                .name(question.getName())
                .build();
    }

    public Question to(QuestionDto question) {
        return Question.builder()
                .type(question.getType())
                .name(question.getName())
                .build();
    }


}
