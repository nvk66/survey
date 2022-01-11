package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.DuplicateException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.repository.UniversityRepository;
import edu.akorzh.survey.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public University add(University university) {
        if (universityRepository.findByGuidAndName(university.getGuid(), university.getName()) != null) {
            throw new DuplicateException();
        }
        universityRepository.save(university);
        return university;
    }

    @Override
    @Transactional
    public List<University> get() {
        return universityRepository.findAll();
    }

    @Override
    public University get(String guid) {
        return universityRepository.findByGuid(guid).orElseThrow(NotFoundException::new);
    }
}
