package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements IDeleteCategoryService {

    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";

    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    private CategoryDtoService dtoService;

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Category category = getCategory(id);
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }
    public List<Category> findAll(){
        List <Category> entities = categoryRepository.findAll();
        return entities;
    }
    
    public List<CategoryDto> findDto() throws Exception{
        
        return dtoService.findAll();
    }
    
    private Category getCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty() || categoryOptional.get().isSoftDelete()) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }
        return categoryOptional.get();
    }    
}
