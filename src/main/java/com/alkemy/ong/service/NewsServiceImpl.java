package com.alkemy.ong.service;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.repository.INewsRepository;

import javax.persistence.Column;


@Service
public class NewsServiceImpl {

    @Autowired
    INewsRepository INewsRepository;

    public News postNews(String name, String content, String image, Category category) throws OperationNotAllowedException {
        if (name.isEmpty()) {
            throw new OperationNotAllowedException("El titulo no puede estar vacío");
        }
        if (content.isEmpty()) {
            throw new OperationNotAllowedException("Debe contener información");
        }
        if (image.isEmpty()) {
            throw new OperationNotAllowedException("Debe agregar una imagen");
        }

        News news = new News();
        news.setName(name);
        news.setContent(content);
        news.setImage(image);
        news.setCategory(category);

        return INewsRepository.save(news);
    }
}
