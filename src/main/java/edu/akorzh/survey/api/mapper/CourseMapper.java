package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.CourseDto;
import edu.akorzh.survey.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final TeacherMapper teacherMapper;
    private final GroupMapper groupMapper;
    private final SubjectMapper subjectMapper;

    public CourseDto to(Course course) {
        return CourseDto.builder()
                .groupId(course.getGroup().getId())
                .teacherId(course.getTeacher().getId())
                .subjectId(course.getSubject().getId())
                .hours(course.getHours())
                .since(course.getSince())
                .till(course.getTill())
                .group(groupMapper.to(course.getGroup()))
                .teacher(teacherMapper.to(course.getTeacher()))
                .subject(subjectMapper.to(course.getSubject()))
                .build();
    }
}
