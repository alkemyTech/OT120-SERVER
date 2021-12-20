package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.model.response.CategoryDtoMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoService {

    @Autowired
    private CategoryDtoMapping categoryDtoMapping;
   
   
    public List<CategoryDto> findAll() throws Exception {
        List<CategoryDto> entities = categoryDtoMapping.findAll();
        return entities;
    }   
}
