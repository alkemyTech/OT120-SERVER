package com.alkemy.ong.service;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService {

    //private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

    @Autowired
    private INewsRepository newsRepository;


    @Override
    public NewsDTO getNewsDTO(Long id) {
        var newsDTO = new NewsDTO();
        try {
            var news = this.getNewsById(id);
            newsDTO.setName(news.getName());
            newsDTO.setImage(news.getImage());
            newsDTO.setContent(news.getContent());
            //Aca deberiamos agregar el id de la categoria?
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
        return newsDTO;
    }

    @Override
    public boolean existId(Long id) {
        return newsRepository.existsById(id);
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new IllegalStateException(String.format("News with ID %n doesn't exist %s", id)));
    }


     /*
    public News getNews(Long id) {
        Optional<News> newsOptional =  newsRepository.findById(id);
        if (newsOptional.isEmpty() || newsOptional.get().isSoftDelete()) {
            throw new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE);
        }
        return newsOptional.get();
    }
    */
}
