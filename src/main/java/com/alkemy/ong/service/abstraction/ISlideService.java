package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface ISlideService {

  void delete(Long id) throws EntityNotFoundException;

  SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;

}