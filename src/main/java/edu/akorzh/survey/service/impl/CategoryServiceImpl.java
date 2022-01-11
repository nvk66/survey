package edu.akorzh.survey.service.impl;

import edu.akorzh.survey.exception.NotFoundException;
import edu.akorzh.survey.model.Category;
import edu.akorzh.survey.model.Survey;
import edu.akorzh.survey.repository.CategoryRepository;
import edu.akorzh.survey.repository.SurveyRepository;
import edu.akorzh.survey.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final SurveyRepository surveyRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category add(Category category, Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NotFoundException::new);

        category.setSurvey(survey);

        categoryRepository.save(category);

        return category;
    }

    @Override
    @Transactional
    public Set<String> findAllPublic() {
        return categoryRepository.findAllByCommonTrue().stream().map(Category::getName).collect(Collectors.toSet());
    }

    @Override
    public List<Category> getBySurvey(Long id) {
        return categoryRepository.findAllBySurvey(surveyRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public Category get(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
    }
}
