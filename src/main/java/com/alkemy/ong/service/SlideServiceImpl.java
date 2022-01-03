package com.alkemy.ong.service;

<<<<<<< HEAD
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.SlideMapper;
=======
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.repository.IOrganizationRepository;
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ISlideService;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Optional;
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";

    @Autowired
    private ISlideRepository slideRepository;

<<<<<<< HEAD
    @Autowired
    private SlideMapper slideMapper;

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!slideRepository.existsById(id)) {
            throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
        }
        slideRepository.deleteById(id);
=======
  @Autowired
  private SlideMapper slideMapper;

  @Autowired
  private IOrganizationRepository organizationRepository;

  @Autowired
  private OrganizationMapper organizationMapper;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    if (!slideRepository.existsById(id)) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
    }

<<<<<<< HEAD
    @Override
    public List<SlideDto> listAll() {
        if (slideRepository.listAllByOrder() == null) {
            throw new NotFoundExceptions(SLIDE_NOT_FOUND_MESSAGE);
        }
        return slideMapper.entity2DtoList(slideRepository.listAllByOrder());
    }
=======
  @Override
  public SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException {
    Optional<Slide> result = slideRepository.findById(id);

    if(result.isPresent()) {

      Slide slide = slideMapper.slideReqDto2Entity(slideReqDto);
      slide.setImageUrl(slideReqDto.imageUrl);
      slide.setText(slideReqDto.text);
      slide.setOrder(slideReqDto.order);

      Organization organization = organizationRepository.getById(slideReqDto.organizationId);
      slide.setOrganizationId(organization);

      slide.setId(id);
      slideRepository.save(slide);
      return slideMapper.slideEntity2Dto(slide);

    } else {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
  }

>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
}

