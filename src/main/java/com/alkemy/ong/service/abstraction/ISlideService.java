package com.alkemy.ong.service.abstraction;

<<<<<<< HEAD
import com.alkemy.ong.dto.SlideDto;
=======
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;

>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface ISlideService {

    void delete(Long id) throws EntityNotFoundException;

    List<SlideDto> listAll();

<<<<<<< HEAD
  SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;
=======

>>>>>>> ba78f839e6195d5665569e23a32e33054a904670
}
