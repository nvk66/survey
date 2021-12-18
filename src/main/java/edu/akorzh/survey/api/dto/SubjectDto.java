package edu.akorzh.survey.api.dto;

import edu.akorzh.survey.common.RatingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private String name;

    private String hours;

    private RatingType ratingType;

}
