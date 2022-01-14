package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.ICategoryService;

import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class CategoryServiceImpl implements ICategoryService {


    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";
    private static final int SIZE_DEFAULT = 10;

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

//    public List<CategoryDto> findAll() {
//        return categoryRepository.findAll().stream()
//                .map(category -> categoryMapper.categoryToCategoryDto(category))
//                .collect(Collectors.toList());
//    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) throws EntityNotFoundException {
        Optional<Category> categoryEntity = this.categoryRepository.findById(id);

        if (!categoryEntity.isPresent()) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }
        this.categoryMapper.categoryRefreshValue(categoryEntity.get(), categoryDto);
        Category categorySaved = this.categoryRepository.save(categoryEntity.get());
        CategoryDto result = this.categoryMapper.categoryToCategoryDto(categorySaved);

        return result;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        Category categorySaved = categoryRepository.save(category);
        CategoryDto result = categoryMapper.categoryToCategoryDto(categorySaved);

        return result;
    }

//    @Override
//    public Page<Category> pagination(int pageSize, int pageNumber) {
//        Pageable page = PageRequest.of(pageNumber, pageSize);
//        Pageable prev = page.previousOrFirst();
//        Pageable next = page.next();
//
//        return categoryRepository.findAll(page);
//    }


//    @Override
//    public List<CategoryDto> findAll() throws NotFoundException {
//        List<CategoryDto> dto = categoryRepository.findAll()
//                .stream()
//                .map(entity -> categoryMapper.categoryToCategoryDto(entity))
//                .collect(Collectors.toList());
//        if (dto.isEmpty()) {
//            throw new NotFoundException("Emtpy message");
//        }
//        return dto;
//    }
//
//    @Override
//    public Page<Category> readAll(Pageable pageable, int page) throws NotFoundException {
//        pageable = (Pageable) PageRequest.of(page, SIZE_DEFAULT);
//        if (page > categoryRepository.findAll(pageable).getTotalPages()) {
//            throw new NotFoundException("Page not found");
//        }
//        return categoryRepository.findAll((org.springframework.data.domain.Pageable) pageable);
//    }
//
//    @Override
//    public Optional<Category> findByid(Long id) {
//        return categoryRepository.findById(id);
//    }

    @Override
    public PageDto<CategoryDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException {
        Pageable pageable = PageRequest.of(page, sizePage, Sort.by(sortBy));
        Page<Category> pageRecovered = categoryRepository.findAll(pageable);
        Integer totalPages = pageRecovered.getTotalPages();

        if (totalPages < page) {
            throw new NotFoundException("The page does not exists");
        }
        return categoryMapper.toPageDto(pageRecovered, page, totalPages);
    }
}
