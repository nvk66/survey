package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.SubjectDto;
import edu.akorzh.survey.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto to(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .ratingType(subject.getRatingType())
                .build();
    }

    public Subject to(SubjectDto subject) {
        return Subject.builder()
                .name(subject.getName())
                .ratingType(subject.getRatingType())
                .build();
    }

}
