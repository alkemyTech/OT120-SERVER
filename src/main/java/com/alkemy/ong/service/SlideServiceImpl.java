package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
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
  SlideMapper slideMapper;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    if (!slideRepository.existsById(id)) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    slideRepository.deleteById(id);
  }

  @Override
  public List<SlideDtoGet> getAllSlidesByOrganization(Organization organization) {
    List<Slide> slides = slideRepository.findAllByOrganizationId(organization);
    return slideMapper.toSlideDtoGetList(slides);
  }

}

