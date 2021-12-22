package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.dto.OrganizationDto;

public interface IOrganizationService {

    OrganizationDto getById(Long id);

    OrganizationAllDto update(OrganizationAllDto dto, Long id) throws Exception;
}
