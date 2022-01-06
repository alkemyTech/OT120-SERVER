package com.alkemy.ong.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.TestimonialRequestDto;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.ITestimonialService;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

  private static final String TESTIMONIAL_NOT_FOUND_MESSAGE = "Testimonial not found.";
  
  @Autowired
  private ITestimonialRepository testimonialRepository;

  @Autowired
  private TestimonialMapper testimonialMapper;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    Testimonial testimonial = getTestimonial(id);
    testimonial.setSoftDelete(true);
    testimonialRepository.save(testimonial);
  }

  private Testimonial getTestimonial(Long id) {
    Optional<Testimonial> testimonialOptional = testimonialRepository.findById(id);
    if (testimonialOptional.isEmpty() || testimonialOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(TESTIMONIAL_NOT_FOUND_MESSAGE);
    }
    return testimonialOptional.get();
  }

  @Override
  public Testimonial save(TestimonialRequestDto testimonialRequest) {
    Testimonial testimonial = testimonialMapper.dto2Entity(testimonialRequest);
    return testimonialRepository.save(testimonial);
  }
  
  @Override
  public TestimonialRequestDto update(TestimonialRequestDto dto, Long id) {
    if (!testimonialRepository.existsById(id)) { 
      throw new EntityNotFoundException(TESTIMONIAL_NOT_FOUND_MESSAGE); 
    }
	Testimonial entity = testimonialRepository.getById(id);
    testimonialMapper.refreshValues(dto, entity);
    testimonialRepository.save(entity);
    return testimonialMapper.entity2Dto(testimonialRepository.getById(id));
  }
}
