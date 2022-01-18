package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Course;
import edu.akorzh.survey.repository.CourseRepository;
import edu.akorzh.survey.repository.PermissionRepository;
import edu.akorzh.survey.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public Course get(Long permissionId) {
        return permissionRepository.findById(permissionId).orElseThrow(NotFoundException::new).getCourse();
    }
}
