package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.repository.GroupRepository;
import edu.akorzh.survey.repository.UniversityRepository;
import edu.akorzh.survey.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final UniversityRepository universityRepository;
    private final GroupRepository groupRepository;


    @Override
    @Transactional
    public Group add(Group group, Long universityId) {
        University university = universityRepository.findById(universityId).orElseThrow(NotFoundException::new);
        group.setUniversity(university);
        groupRepository.save(group);
        return group;
    }

    @Override
    @Transactional
    public List<Group> getAllByUniversity(Long universityId) {
        return groupRepository.findAllByUniversity(
                universityRepository.findById(universityId).orElseThrow(NotFoundException::new));
    }
}
