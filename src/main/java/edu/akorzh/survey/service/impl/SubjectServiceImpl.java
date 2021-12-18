package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.*;
import edu.akorzh.survey.repository.*;
import edu.akorzh.survey.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final UniversityRepository universityRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupSubjectTeacherLinkRepository groupSubjectTeacherLinkRepository;

    @Override
    @Transactional
    public Subject add(Subject subject, Long universityId) {
        University university = universityRepository.findById(universityId).orElseThrow(NotFoundException::new);
        subject.setUniversity(university);
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    @Transactional
    public void addSubjectToGroup(Long groupId,
                                  Long subjectId,
                                  Long teacherId,
                                  GroupSubjectTeacherLink groupSubjectTeacherLink) {

        Group group = groupRepository.findById(groupId).orElseThrow(NotFoundException::new);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(NotFoundException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(NotFoundException::new);

        groupSubjectTeacherLink.setGroup(group);
        groupSubjectTeacherLink.setSubject(subject);
        groupSubjectTeacherLink.setTeacher(teacher);

        groupSubjectTeacherLinkRepository.save(groupSubjectTeacherLink);
    }

}
