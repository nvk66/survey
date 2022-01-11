package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Course;
import edu.akorzh.survey.model.Subject;

import java.util.List;

public interface SubjectService {
    Subject add(Subject subject, Long universityId);

    void addSubjectToGroup(Long groupId,
                              Long subjectId,
                              Long teacherId,
                              Course course);

    List<Subject> get(Long universityId);
}
