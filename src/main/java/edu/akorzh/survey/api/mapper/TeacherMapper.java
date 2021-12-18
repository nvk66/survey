package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.TeacherDto;
import edu.akorzh.survey.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDto to(Teacher teacher) {
        return TeacherDto.builder()
                .grade(teacher.getGrade())
                .teachingDate(teacher.getTeachingDate())
                .submitted(teacher.getSubmitted())
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
