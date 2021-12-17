
package com.alkemy.ong.model.response;

import com.alkemy.ong.model.request.CategoryDto;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.CategoryDtoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoMapping{
    
    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    private CategoryDtoService categoryService;
    
    public List<CategoryDto> findAll()throws Exception{
        try {      
            return categoryService.findAll();
        } catch (Exception e) {  
            throw new Exception(e.getMessage());
        }
    }
}
