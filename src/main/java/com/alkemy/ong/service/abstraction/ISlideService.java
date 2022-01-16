package com.alkemy.ong.service.abstraction;


import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;


public interface ISlideService {

    void delete(Long id) throws EntityNotFoundException;

    SlideDto save(SlideDto slideDto) throws EntityNotFoundException, IOException;

    SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;

    List<SlideRequestDto> listAll();

    SlideResponseDto getById(Long id) throws EntityNotFoundException;

}
