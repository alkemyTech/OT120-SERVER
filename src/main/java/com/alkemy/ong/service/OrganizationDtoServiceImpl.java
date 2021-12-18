package com.alkemy.ong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.model.request.OrganizationDto;
import com.alkemy.ong.model.response.mapper.OrganizationMapper;
import com.alkemy.ong.service.abstraction.IOrganizationDtoService;
import com.alkemy.ong.service.abstraction.IOrganizationService;

@Service
public class OrganizationDtoServiceImpl implements IOrganizationDtoService {

  @Autowired
  private IOrganizationService organizationService;

  @Autowired
  private OrganizationMapper organizationMapper;

  @Override
  public OrganizationDto getById(Long id) {
    return organizationMapper.organizationEntity2DTO(organizationService.getById(id));
  }

}
