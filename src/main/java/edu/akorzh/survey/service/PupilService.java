package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Pupil;

public interface PupilService {
    Pupil add(Pupil pupil, Long groupId, String userId);
}
