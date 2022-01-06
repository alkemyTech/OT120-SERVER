package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.model.entity.Slide;
import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface ISlideService {

    void delete(Long id) throws EntityNotFoundException;

    SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;

    List<SlideRequestDto> listAll();

    SlideResponseDto getById(Long id) throws EntityNotFoundException;

}
