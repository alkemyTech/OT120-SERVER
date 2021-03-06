package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDtoOrganization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.model.entity.Organization;

import java.sql.Timestamp;
import java.util.List;

@Component
public class OrganizationMapper {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private SlideMapper slideMapper;

  public OrganizationDto organizationEntity2Dto(Organization entity, List<SlideDtoOrganization> slideDtoList) {
    OrganizationDto dto = new OrganizationDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setImage(entity.getImage());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    dto.setFacebook(entity.getFacebook());
    dto.setInstagram(entity.getInstagram());
    dto.setLinkedin(entity.getLinkedin());
    dto.setSlideDtoList(slideDtoList);

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

    public Organization organizationDtoToEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setImage(organizationDto.getImage());
        organization.setPhone(organizationDto.getPhone());
        organization.setAddress(organizationDto.getAddress());
        organization.setEmail(organizationDto.getEmail());
        organization.setWelcomeText(organizationDto.getWelcomeText());
        organization.setAboutUsText(organizationDto.getAboutUsText());
        organization.setFacebook(organizationDto.getFacebook());
        organization.setInstagram(organizationDto.getInstagram());
        organization.setLinkedin(organizationDto.getLinkedin());

        return organization;
    }

    public OrganizationDto organizationEntity2Dto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName(organization.getName());
        organizationDto.setImage(organization.getImage());
        organizationDto.setPhone(organization.getPhone());
        organizationDto.setAddress(organization.getAddress());
        organizationDto.setEmail(organization.getEmail());
        organizationDto.setWelcomeText(organization.getWelcomeText());
        organizationDto.setAboutUsText(organization.getAboutUsText());
        organizationDto.setFacebook(organization.getFacebook());
        organizationDto.setInstagram(organization.getInstagram());
        organizationDto.setLinkedin(organization.getLinkedin());

        return organizationDto;
    }

}
