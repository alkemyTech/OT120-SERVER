package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.enums.exception.NotFoundExceptions;
import com.alkemy.ong.dto.mapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.abstraction.INewsService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

    private static final int SIZE_DEFAULT = 10;

    @Autowired
    INewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public NewsDto findNewsById(Long id) throws EntityNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE));
        return newsMapper.newsToDto(news);
    }

    @Override
    public NewsDto postNews(NewsDto newsDto) {
        News newsEntity = newsMapper.newsDtoToEntity(newsDto);
        News saved = newsRepository.save(newsEntity);
        NewsDto result = newsMapper.newsEntityToDto(saved);
        return result;
    }

    @Override
    public NewsDto updateNews(NewsDto newsDto, Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if (optional.isPresent()) {
            return newsMapper.newsEntityToDto(newsRepository.save(newsMapper.updateValues(newsDto, optional.get())));
        } else
            throw new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE);
    }

    public void delete(Long id) throws EntityNotFoundException {
        News news = getNews(id);
        news.setSoftDelete(true);
        newsRepository.save(news);
    }

    private News getNews(Long id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        if (newsOptional.isEmpty()) {
            throw new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE);
        }
        return newsOptional.get();
    }

    @Override
    public PageDto<NewsDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundExceptions {
        Pageable pageable = PageRequest.of(page, sizePage, Sort.by(sortBy));
        Page<News> pageRecovered = newsRepository.findAll(pageable);
        Integer totalPages = pageRecovered.getTotalPages();

        if (totalPages < page) {
            throw new NotFoundExceptions ("The page does not exist.");
        }
        return newsMapper.toPageDto(pageRecovered, page, totalPages);
    }
}