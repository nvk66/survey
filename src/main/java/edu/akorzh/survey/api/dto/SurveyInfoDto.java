package edu.akorzh.survey.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.akorzh.survey.common.SurveyType;
import edu.akorzh.survey.model.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyInfoDto {

    private Long id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    private SurveyType type;

    private SubjectDto subject;

    private TeacherDto teacher;

    private GroupDto group;

    private Long permissionId;
}
