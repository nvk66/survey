package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.DuplicateException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.model.Pupil;
import edu.akorzh.survey.model.User;
import edu.akorzh.survey.repository.GroupRepository;
import edu.akorzh.survey.repository.PupilRepository;
import edu.akorzh.survey.repository.UserRepository;
import edu.akorzh.survey.service.PupilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class PupilServiceImpl implements PupilService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PupilRepository pupilRepository;

    @Override
    @Transactional
    public Pupil add(Pupil pupil, Long groupId, String userName) {
        Group group = groupRepository.findById(groupId).orElseThrow(NotFoundException::new);
        User user = userRepository.findUserByLogin(userName).orElseThrow(NotFoundException::new);

        if (pupilRepository.findByUser(user) != null) {
            throw new DuplicateException();
        }

        pupil.setGroup(group);
        pupil.setUser(user);

        pupilRepository.save(pupil);
        return pupil;
    }
}
