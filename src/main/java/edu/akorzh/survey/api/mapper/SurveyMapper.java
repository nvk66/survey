package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.SurveyDto;
import edu.akorzh.survey.model.Survey;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SurveyMapper {

    public SurveyDto to(Survey survey) {
        return SurveyDto.builder()
                .common(survey.getCommon())
                .createdDate(survey.getCreatedDate())
                .type(survey.getType())
                .name(survey.getName())
                .build();
    }

    public Survey to(SurveyDto survey) {
        return Survey.builder()
                .common(survey.getCommon())
                .createdDate(LocalDate.now())
                .type(survey.getType())
                .name(survey.getName())
                .build();
    }


}
