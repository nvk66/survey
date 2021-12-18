package edu.akorzh.survey.service;

import edu.akorzh.survey.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category add(Category category, Long surveyId);

    Set<String> findAllPublic();
}
