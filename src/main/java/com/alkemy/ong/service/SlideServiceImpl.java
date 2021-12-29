package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ISlideService;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
  public SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException {
    Optional<Slide> result = slideRepository.findById(id);

    if(result.isPresent()) {

      Slide slide = slideMapper.slideReqDto2Entity(slideReqDto);
      slide.setImageUrl(slideReqDto.imageUrl);
      slide.setText(slideReqDto.text);
      slide.setOrder(slideReqDto.order);

      Organization organization = new Organization();
      organization.setId(slideReqDto.organizationId);
      slide.setOrganizationId(organization);

      slide.setId(id);
      slideRepository.save(slide);
      SlideResponseDto updatedSlide = slideMapper.slideEntity2Dto(slide);

      return updatedSlide;

    } else {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);

    }
  }
}

