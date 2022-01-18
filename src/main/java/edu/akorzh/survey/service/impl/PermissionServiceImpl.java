package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Course;
import edu.akorzh.survey.model.Permission;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.repository.CourseRepository;
import edu.akorzh.survey.repository.PermissionRepository;
import edu.akorzh.survey.repository.SurveyRepository;
import edu.akorzh.survey.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final SurveyRepository surveyRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Permission add(Long surveyId, Long courseId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);

        return permissionRepository.save(
                Permission.builder()
                        .course(course)
                        .survey(survey)
                        .build());
    }

    @Override
    public Permission get(Long permissionId) {
        return permissionRepository.findById(permissionId).orElseThrow(NotFoundException::new);
    }
}
