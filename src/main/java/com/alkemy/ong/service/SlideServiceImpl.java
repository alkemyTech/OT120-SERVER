package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ISlideService;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";

    @Autowired
    private ISlideRepository slideRepository;

    @Autowired
    private SlideMapper slideMapper;

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!slideRepository.existsById(id)) {
            throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
        }
        slideRepository.deleteById(id);
    }

    @Override
    public List<SlideDto> listAll() {
        if (slideRepository.listAllByOrder() == null) {
            throw new NotFoundExceptions(SLIDE_NOT_FOUND_MESSAGE);
        }
        return slideMapper.entity2DtoList(slideRepository.listAllByOrder());
    }
}

