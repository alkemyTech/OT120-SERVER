package com.alkemy.ong.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.service.abstraction.IDeleteOrganizationService;
import com.alkemy.ong.service.abstraction.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService, IDeleteOrganizationService {

  private static final String ORGANIZATION_NOT_FOUND_MESSAGE = "Organization not found.";

  @Autowired
  private IOrganizationRepository organizationRepository;

  @Override
  public Organization getById(Long id) {
    Optional<Organization> organizationOptional = organizationRepository.findById(id);
    if (organizationOptional.isEmpty() || organizationOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
    }
    return organizationOptional.get();
  }

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    Organization organization = getById(id);
    organization.setSoftDelete(true);
    organizationRepository.save(organization);
  }

}
