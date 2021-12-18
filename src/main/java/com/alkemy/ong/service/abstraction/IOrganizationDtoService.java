package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.OrganizationDto;

public interface IOrganizationDtoService {

  OrganizationDto getById(Long id);

}
