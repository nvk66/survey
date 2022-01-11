package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.CourseDto;
import edu.akorzh.survey.api.dto.SubjectDto;
import edu.akorzh.survey.api.mapper.SubjectMapper;
import edu.akorzh.survey.model.Course;
import edu.akorzh.survey.model.Subject;
import edu.akorzh.survey.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectMapper subjectMapper;
    private final SubjectService subjectService;

    @PostMapping("/{universityId}/")
    public SubjectDto add(@PathVariable(value = "universityId") Long universityId,
                          @Validated @RequestBody SubjectDto subjectDto) {
        final Subject subject = subjectService.add(subjectMapper.to(subjectDto), universityId);
        return subjectMapper.to(subject);
    }

    @GetMapping("/{universityId}/")
    public List<SubjectDto> get(@PathVariable(value = "universityId") Long universityId) {
        return subjectService.get(universityId).stream().map(subjectMapper::to).collect(Collectors.toList());
    }

    @PostMapping("/{subjectId}/group/{groupId}/teacher/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSubjectToGroup(@PathVariable(value = "groupId") Long groupId,
                                  @PathVariable(value = "subjectId") Long subjectId,
                                  @PathVariable(value = "teacherId") Long teacherId,
                                  @Validated @RequestBody CourseDto courseDto) {
        subjectService.addSubjectToGroup(groupId, subjectId, teacherId,
                Course.builder()
                        .since(courseDto.getSince())
                        .till(courseDto.getTill())
                        .build());
    }


}
