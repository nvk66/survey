package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Category;
import edu.akorzh.survey.model.Question;
import edu.akorzh.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    List<Question> findAllBySurveyAndCategory(Survey survey, Category category);

    List<Question> findAllBySurvey(Survey survey);
}
