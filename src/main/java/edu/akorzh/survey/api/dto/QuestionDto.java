package edu.akorzh.survey.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.akorzh.survey.common.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    private Long id;

    private String name;

    private AnswerType type;

    private Long categoryId;

}
