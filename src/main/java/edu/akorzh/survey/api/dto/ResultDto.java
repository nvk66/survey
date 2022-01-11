package edu.akorzh.survey.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto {

    private Long questionId;

    private String questionName;

    private Long categoryId;

    private BigDecimal average;

    private List<String> answers;

}
