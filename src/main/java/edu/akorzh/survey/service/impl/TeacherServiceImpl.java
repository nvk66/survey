package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.DuplicateException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.*;
import edu.akorzh.survey.repository.TeacherRepository;
import edu.akorzh.survey.repository.UniversityRepository;
import edu.akorzh.survey.repository.UserRepository;
import edu.akorzh.survey.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public Teacher add(Teacher teacher, String userName) {
        User user = userRepository.findUserByLogin(userName).orElseThrow(NotFoundException::new);
        if (teacherRepository.findByUser(user) != null) {
            throw new DuplicateException();
        }
        teacher.setUser(user);
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    @Transactional
    public List<Teacher> get(Long universityId) {
        return teacherRepository.findAllByUniversity(
                universityRepository.findById(universityId).orElseThrow(NotFoundException::new));
    }


}
