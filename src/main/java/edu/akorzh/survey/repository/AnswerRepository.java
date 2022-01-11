package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Answer;
import edu.akorzh.survey.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

    List<Answer> findAllByQuestionIn(List<Question> questions);
}
