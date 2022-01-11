package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.ActionNotAvailableException;
import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Category;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.repository.AnswerRepository;
import edu.akorzh.survey.repository.CategoryRepository;
import edu.akorzh.survey.repository.QuestionRepository;
import edu.akorzh.survey.repository.SurveyRepository;
import edu.akorzh.survey.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final CategoryRepository categoryRepository;
    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public Question add(Question question, Long surveyId, Long categoryId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);

        question.setCategory(category);
        question.setSurvey(survey);

        questionRepository.save(question);

        return question;
    }

    @Override
    @Transactional
    public List<Question> get(Long surveyId, Long categoryId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);

        return questionRepository.findAllBySurveyAndCategory(survey, category);
    }

    @Override
    @Transactional
    public void delete(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);

        List<Question> questions = questionRepository.findAllBySurvey(question.getSurvey());

        List<Answer> answers = answerRepository.findAllByQuestionIn(questions);

        if (!answers.isEmpty()) {
            throw new ActionNotAvailableException("action.not.available.delete.question");
        }

        questionRepository.delete(question);
    }
}
