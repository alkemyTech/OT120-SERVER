package com.alkemy.ong.model.response.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.request.OrganizationDto;

@Component
public class OrganizationMapper {

  public OrganizationDto organizationEntity2DTO(Organization entity) {
    OrganizationDto dto = new OrganizationDto();
    dto.setName(entity.getName());
    dto.setImage(entity.getImage());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    return dto;
  }

}
