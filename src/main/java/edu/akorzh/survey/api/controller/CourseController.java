package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.CourseDto;
import edu.akorzh.survey.api.mapper.CourseMapper;
import edu.akorzh.survey.model.Course;
import edu.akorzh.survey.service.CourseService;
import edu.akorzh.survey.service.SubjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final SubjectService subjectService;
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSubjectToGroup(@Validated @RequestBody CourseDto courseDto) {
        subjectService.addSubjectToGroup(courseDto.getGroupId(), courseDto.getSubjectId(),
                courseDto.getTeacherId(), Course.builder()
                        .since(courseDto.getSince())
                        .till(courseDto.getTill())
                        .hours(courseDto.getHours())
                        .build());
    }

    @GetMapping("/{id}")
    public CourseDto get(@PathVariable(value = "id") Long id) {
        return courseMapper.to(courseService.get(id));
    }

}
