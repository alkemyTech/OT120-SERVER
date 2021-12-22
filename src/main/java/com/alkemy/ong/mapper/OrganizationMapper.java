package com.alkemy.ong.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.model.entity.Organization;

@Component
public class OrganizationMapper {

    public OrganizationDto organizationEntity2Dto(Organization entity) {
        OrganizationDto dto = new OrganizationDto();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public Organization organizationDto2Entity(OrganizationDto dto) {
        Organization entity = new Organization();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        return entity;
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

}
