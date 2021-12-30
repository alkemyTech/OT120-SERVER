package com.alkemy.ong.service;

import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.dto.CategoryRequest;
import com.alkemy.ong.dto.CategoryResponse;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.ICategoryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CategoryServiceImpl implements ICategoryService {


    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Category category = getCategory(id);
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty() || categoryOptional.get().isSoftDelete()) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }
        return categoryOptional.get();
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.categoryToCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse update(long id, CategoryRequest categoryDto) throws EntityNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);

        if (result.isPresent()) {

            Category category = categoryMapper.categoryRequest2Entity(categoryDto);
            category.setName(categoryDto.name);
            category.setDescription(categoryDto.description);
            category.setImage(categoryDto.image);

            category.setId(id);
            categoryRepository.save(category);
            CategoryResponse updatedCategory = categoryMapper.category2Dto(category);

            return updatedCategory;

        } else {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtotoCategory(categoryDto);
        Category categorySaved = categoryRepository.save(category);
        CategoryDto result = categoryMapper.categoryToCategoryDto(categorySaved);

        return result;
    }
}
