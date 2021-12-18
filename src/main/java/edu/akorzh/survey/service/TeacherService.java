package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Teacher;

public interface TeacherService {
    Teacher add(Teacher teacher, Long universityId, String userName);
}
