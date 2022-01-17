package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.GroupDto;
import edu.akorzh.survey.api.mapper.GroupMapper;
import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupService groupService;

    @PostMapping("/{universityId}/")
    @RolesAllowed({"ROLE_UNIVERSITY_ADMINISTRATOR", "ROLE_TEACHER"})
    public GroupDto add(@PathVariable(value = "universityId") Long universityId,
                        @Validated @RequestBody GroupDto groupDto) {
        final Group group = groupService.add(groupMapper.to(groupDto), universityId);
        return groupMapper.to(group);
    }

    @GetMapping("/{universityId}/")
    @RolesAllowed("ROLE_USER")
    public List<GroupDto> getAllByUniversity(@PathVariable(value = "universityId") Long universityId) {
        return groupMapper.to(groupService.getAllByUniversity(universityId));
    }



}
