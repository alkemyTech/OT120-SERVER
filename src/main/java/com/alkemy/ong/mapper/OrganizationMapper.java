package com.alkemy.ong.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.model.entity.Organization;

import java.sql.Timestamp;

@Component
public class OrganizationMapper {


@Autowired
ModelMapper modelMapper;

    public OrganizationDto organizationEntity2Dto(Organization entity) {
      OrganizationDto dto = new OrganizationDto();
  
      dto.setName(entity.getName());
      dto.setImage(entity.getImage());
      dto.setPhone(entity.getPhone());
      dto.setAddress(entity.getAddress());
      dto.setFacebook(entity.getFacebook());
      dto.setLinkedin(entity.getLinkedin());
      dto.setInstagram(entity.getInstagram());
  
      return dto;
    }
    public Organization organizationDto2Entity(OrganizationDto organizationDto) {
      return modelMapper.map(organizationDto, Organization.class);
    }

    public Organization organizationDto2EntityAll(OrganizationAllDto dto) {
        Organization entity = new Organization();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setWelcomeText(dto.getWelcomeText());
        entity.setAboutUsText(dto.getAboutUsText());
        entity.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        return entity;
    }

    public OrganizationAllDto organizationEntity2DtoAll(Organization entity) {
        OrganizationAllDto dto = new OrganizationAllDto();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setAboutUsText(entity.getAboutUsText());

        return dto;
    }

    public Organization updateValues(OrganizationAllDto dto, Organization entity) {
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setWelcomeText(dto.getWelcomeText());
        entity.setAboutUsText(dto.getAboutUsText());
        return entity;
    }
  }
