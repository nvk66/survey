package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.TeacherDto;
import edu.akorzh.survey.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDto to(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .grade(teacher.getGrade())
                .teachingDate(teacher.getTeachingDate())
                .submitted(teacher.getSubmitted())
                .firstName(teacher.getUser().getFirstName())
                .lastName(teacher.getUser().getLastName())
                .patronymic(teacher.getUser().getPatronymic())
                .build();
    }

    public Teacher to(TeacherDto teacher) {
        return Teacher.builder()
                .grade(teacher.getGrade())
                .teachingDate(teacher.getTeachingDate())
                .submitted(teacher.getSubmitted())
                .build();
    }

}
