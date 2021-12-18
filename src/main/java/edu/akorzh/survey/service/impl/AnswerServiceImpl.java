package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.repository.AnswerRepository;
import edu.akorzh.survey.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public void add(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }
}
