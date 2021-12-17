
package com.alkemy.ong.model.entity.dto;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoMapping{
    
    @Autowired
    private ICategoryRepository categoryRepository;
    
    public List<CategoryDto> findAll()throws Exception{
        try {
            List<Category> entities = categoryRepository.findAll();
            List<CategoryDto> dtos  = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for(Category category : entities){
                dtos.add(modelMapper.map(category, CategoryDto.class));
            }
            return dtos;
        } catch (Exception e) {  
            throw new Exception(e.getMessage());
        }
    }
}
