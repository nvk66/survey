package edu.akorzh.survey.service;

import edu.akorzh.survey.model.University;

import java.util.List;

public interface UniversityService {

    University add(University university);

    List<University> get();

    University get(String guid);
}
