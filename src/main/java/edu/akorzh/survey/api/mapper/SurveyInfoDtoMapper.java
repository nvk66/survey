package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.SurveyInfoDto;
import edu.akorzh.survey.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyInfoDtoMapper {

    private final SubjectMapper subjectMapper;
    private final GroupMapper groupMapper;
    private final TeacherMapper teacherMapper;

    public SurveyInfoDto to(Permission permission) {
        return SurveyInfoDto.builder()
                .createdDate(permission.getSurvey().getCreatedDate())
                .type(permission.getSurvey().getType())
                .name(permission.getSurvey().getName())
                .group(groupMapper.to(permission.getCourse().getGroup()))
                .teacher(teacherMapper.to(permission.getCourse().getTeacher()))
                .subject(subjectMapper.to(permission.getCourse().getSubject()))
                .id(permission.getSurvey().getId())
                .permissionId(permission.getId())
                .build();
    }

}
