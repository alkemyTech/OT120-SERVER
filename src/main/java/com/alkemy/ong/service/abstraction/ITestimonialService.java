package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface ITestimonialService {

  void delete(Long id) throws EntityNotFoundException;
}
