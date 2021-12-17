package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.NewsDTO;
import org.springframework.stereotype.Service;

@Service
public interface INewsService {

    NewsDTO findNewsById(Long id);

}
