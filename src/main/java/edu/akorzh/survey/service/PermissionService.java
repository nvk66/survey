package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Permission;

public interface PermissionService {
    Permission add(Long surveyId, Long courseId);

    Permission get(Long permissionId);
}
