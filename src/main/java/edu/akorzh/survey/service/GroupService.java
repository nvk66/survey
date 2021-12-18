package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Group;

import java.util.List;

public interface GroupService {

    Group add(Group group, Long universityId);

    List<Group> getAllByUniversity(Long universityId);
}
