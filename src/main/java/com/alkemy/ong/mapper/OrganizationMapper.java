package com.alkemy.ong.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.model.entity.Organization;

@Component
public class OrganizationMapper {

  @Autowired
  private ModelMapper modelMapper;

  public OrganizationDto organizationEntity2DTO(Organization entity) {
    OrganizationDto dto = new OrganizationDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setImage(entity.getImage());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    dto.setFacebook(entity.getFacebook());
    dto.setInstagram(entity.getInstagram());
    dto.setLinkedin(entity.getLinkedin());
    return dto;
  }
}
