package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;

import javax.persistence.EntityNotFoundException;


public interface ISlideService {

  void delete(Long id) throws EntityNotFoundException;

<<<<<<< HEAD
  SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;
=======

>>>>>>> ba78f839e6195d5665569e23a32e33054a904670
}
