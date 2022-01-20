package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.PupilDto;
import edu.akorzh.survey.api.mapper.PupilMapper;
import edu.akorzh.survey.model.Pupil;
import edu.akorzh.survey.service.PupilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pupils")
public class PupilController {

    private final PupilMapper pupilMapper;
    private final PupilService pupilService;

    @PostMapping("/{groupId}/")
    public PupilDto add(@PathVariable(value = "groupId") Long groupId,
                        @Validated @RequestBody PupilDto pupilDto, Authentication authentication) {
        pupilDto.setSubmitted(false);
        Pupil pupil = pupilService.add(pupilMapper.to(pupilDto), groupId, authentication.getName());
        return pupilMapper.to(pupil);
    }

}
