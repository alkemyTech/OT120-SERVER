package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteOrganizationService {

  void delete(Long id) throws EntityNotFoundException;
  
}
