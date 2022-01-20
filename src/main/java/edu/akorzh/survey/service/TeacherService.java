package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher add(Teacher teacher, String userName);

    List<Teacher> get(Long universityId);
}
