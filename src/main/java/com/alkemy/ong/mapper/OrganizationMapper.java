package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDtoGet;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.OrganizationDto ;
import com.alkemy.ong.model.entity.Organization;

import java.util.List;

@Component
public class OrganizationMapper {

  public OrganizationDto organizationEntity2DTO(Organization entity, List<SlideDtoGet> organizationSlides) {
    OrganizationDto dto = new OrganizationDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setImage(entity.getImage());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    dto.setFacebook(entity.getFacebook());
    dto.setInstagram(entity.getInstagram());
    dto.setLinkedin(entity.getLinkedin());
    dto.setOrganizationSlides(organizationSlides);
    return dto;
  }
}
