package edu.akorzh.survey.api.controller;

import edu.akorzh.survey.api.dto.CategoryDto;
import edu.akorzh.survey.api.mapper.CategoryMapper;
import edu.akorzh.survey.model.Category;
import edu.akorzh.survey.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/survey/{surveyId}/")
    public CategoryDto add(@PathVariable(value = "surveyId") Long surveyId,
                           @Validated @RequestBody CategoryDto categoryDto) {
        final Category category = categoryService.add(categoryMapper.to(categoryDto), surveyId);
        return categoryMapper.to(category);
    }

    @GetMapping("/")
    public Set<String> findPossibleCategories() {
        return categoryService.findAllPublic();
    }


}
