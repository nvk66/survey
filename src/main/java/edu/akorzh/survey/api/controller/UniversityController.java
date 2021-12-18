package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.UniversityDto;
import edu.akorzh.survey.api.mapper.UniversityMapper;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityMapper universityMapper;
    private final UniversityService universityService;

    @PostMapping("/")
    public UniversityDto add(@Validated @RequestBody UniversityDto universityDto) {
        final University university = universityService.add(universityMapper.to(universityDto));
        return universityMapper.to(university);
    }

}
