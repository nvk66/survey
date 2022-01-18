package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.model.Permission;
import edu.akorzh.survey.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/{surveyId}/{courseId}")
    @RolesAllowed("ROLE_USER")
    public Permission add(@PathVariable(value = "surveyId") Long surveyId,
                          @PathVariable(value = "courseId") Long courseId) {
        return permissionService.add(surveyId, courseId);
    }

}
