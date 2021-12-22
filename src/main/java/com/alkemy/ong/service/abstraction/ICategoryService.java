package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface ICategoryService {

  void delete(Long id) throws EntityNotFoundException;

}
