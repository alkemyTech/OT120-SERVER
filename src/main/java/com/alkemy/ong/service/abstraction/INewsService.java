package com.alkemy.ong.service.abstraction;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.entity.News;


public interface INewsService {


     NewsDTO getNewsDTO(Long id);

     boolean existId(Long id);

     News getNewsById(Long id);

}
