package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.UniversityDto;
import edu.akorzh.survey.model.University;
import org.springframework.stereotype.Component;

@Component
public class UniversityMapper {

    public UniversityDto to(University university) {
        return UniversityDto.builder()
                .id(university.getId())
                .name(university.getName())
                .guid(university.getGuid())
                .build();
    }

    public University to(UniversityDto university) {
        return University.builder()
                .name(university.getName())
                .guid(university.getGuid())
                .build();
    }


}
