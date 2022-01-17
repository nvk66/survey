package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.UniversityDto;
import edu.akorzh.survey.api.mapper.UniversityMapper;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityMapper universityMapper;
    private final UniversityService universityService;

    @PostMapping("/")
    @RolesAllowed("ROLE_ADMINISTRATOR")
    public UniversityDto add(@Validated @RequestBody UniversityDto universityDto) {
        final University university = universityService.add(universityMapper.to(universityDto));
        return universityMapper.to(university);
    }

    @GetMapping("/")
    public List<UniversityDto> get() {
        return universityService.get().stream().map(universityMapper::to).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public UniversityDto get(@PathVariable(value = "id") Long id) {
        return universityMapper.to(universityService.getById(id));
    }

//    @GetMapping("/{guid}")
//    public UniversityDto get(@PathVariable(value = "guid") String guid) {
//        return universityMapper.to(universityService.get(guid));
//    }

}
