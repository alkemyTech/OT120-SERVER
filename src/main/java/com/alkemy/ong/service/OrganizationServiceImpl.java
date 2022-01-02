package com.alkemy.ong.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.service.abstraction.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.OrganizationDto ;
import com.alkemy.ong.mapper.OrganizationMapper ;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.service.abstraction.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

  private static final String ORGANIZATION_NOT_FOUND_MESSAGE = "Organization not found.";

  @Autowired
  private IOrganizationRepository organizationRepository;
  
  @Autowired
  private OrganizationMapper organizationMapper;

  @Autowired
  ISlideService slideService;

  @Override
  public OrganizationDto getById(Long id) {
    Organization organization = organizationRepository.getById(id);
    return organizationMapper.organizationEntity2DTO(organization);
  }


}
