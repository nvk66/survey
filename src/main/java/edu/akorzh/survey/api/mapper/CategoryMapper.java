package edu.akorzh.survey.api.mapper;

import edu.akorzh.survey.api.dto.CategoryDto;
import edu.akorzh.survey.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto to(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .common(category.getCommon())
                .build();
    }

    public Category to(CategoryDto category) {
        return Category.builder()
                .name(category.getName())
                .common(Boolean.FALSE)
                .build();
    }

    public List<CategoryDto> to(List<Category> categories) {
        return categories.stream().map(this::to).collect(Collectors.toList());
    }


}
