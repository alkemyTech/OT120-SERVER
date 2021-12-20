
package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.service.CategoryServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapping{
    
    
    @Autowired
    private CategoryServiceImpl categoryService;
            
    public List<CategoryDto> findAll()throws Exception{
        try {      
            ModelMapper modelMapper = new ModelMapper();
            List <Category>entities = categoryService.findAll();
            List <CategoryDto> dtos = new ArrayList();
            entities.forEach(category -> dtos.add(modelMapper.map(category, CategoryDto.class)));
            return dtos;
        } catch (Exception e) {  
            throw new Exception(e.getMessage());
        }
    }
}
