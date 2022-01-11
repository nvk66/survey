package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Category;
import edu.akorzh.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    List<Category> findAllByCommonTrue();

    List<Category> findAllBySurvey(Survey survey);
}
