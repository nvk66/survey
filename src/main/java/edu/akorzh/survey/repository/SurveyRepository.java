package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    @Query(value = "SELECT s FROM Survey s " +
            "LEFT OUTER JOIN FETCH s.permissions p " +
            "LEFT OUTER JOIN FETCH p.course c " +
            "LEFT OUTER JOIN FETCH c.group g " +
            "LEFT OUTER JOIN FETCH c.teacher t " +
            "LEFT OUTER JOIN FETCH c.subject sub " +
            "WHERE g = :group")
    List<Survey> getSurveys(@Param(value = "group") Group group);
}
