package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.model.entity.Organization;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ISlideService {

  void delete(Long id) throws EntityNotFoundException;

  List<SlideDtoGet> getAllSlidesByOrganization(Organization organization);

}
