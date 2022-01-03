package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideDto;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ISlideService {

    void delete(Long id) throws EntityNotFoundException;

    List<SlideDto> listAll();

}
