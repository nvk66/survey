package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.TeacherDto;
import edu.akorzh.survey.api.mapper.TeacherMapper;
import edu.akorzh.survey.model.Teacher;
import edu.akorzh.survey.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherMapper teacherMapper;
    private final TeacherService teacherService;

    @PostMapping("/{universityId}/")
    public TeacherDto add(@PathVariable(value = "universityId") Long universityId,
                          @Validated @RequestBody TeacherDto teacherDto, Authentication authentication) {
        log.info("Adding teacher {} and {}", universityId, teacherDto);
        log.info("Adding teacher {} and {} and {}", universityId, teacherDto, authentication.getName());
        teacherDto.setSubmitted(false);
        final Teacher teacher =
                teacherService.add(teacherMapper.to(teacherDto), universityId, authentication.getName());
        return teacherMapper.to(teacher);
    }

    @GetMapping("/{universityId}/")
    public List<TeacherDto> get(@PathVariable(value = "universityId") Long universityId) {
        return teacherService.get(universityId).stream().map(teacherMapper::to).collect(Collectors.toList());
    }

}
