package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IMembersService {

  void delete(Long id) throws EntityNotFoundException;

}
