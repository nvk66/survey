package edu.akorzh.survey.service;

import edu.akorzh.survey.model.GroupSubjectTeacherLink;
import edu.akorzh.survey.model.Subject;

public interface SubjectService {
    Subject add(Subject subject, Long universityId);

    void addSubjectToGroup(Long groupId,
                              Long subjectId,
                              Long teacherId,
                              GroupSubjectTeacherLink groupSubjectTeacherLink);
}
