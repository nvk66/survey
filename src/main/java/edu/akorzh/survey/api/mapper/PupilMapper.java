package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.PupilDto;
import edu.akorzh.survey.model.Pupil;
import org.springframework.stereotype.Component;

@Component
public class PupilMapper {

    public PupilDto to(Pupil pupil) {
        return PupilDto.builder()
                .recordBook(pupil.getRecordBook())
                .submitted(pupil.getSubmitted())
                .build();
    }

    public Pupil to(PupilDto pupil) {
        return Pupil.builder()
                .recordBook(pupil.getRecordBook())
                .submitted(pupil.getSubmitted())
                .build();
    }

}
