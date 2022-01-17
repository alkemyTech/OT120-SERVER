package com.alkemy.ong.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.alkemy.ong.dto.SlideDtoOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.mapper.OrganizationMapper;
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
    private SlideServiceImpl slideService;

    @Override
    public OrganizationDto getById(Long id){

      Organization organization = organizationRepository.getById(id);

        if(organization.getId() == 0){
            throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
        }

      List<SlideDtoOrganization> slideRequestDtoList = slideService.getOrganizationSlideList(organization);
      return organizationMapper.organizationEntity2Dto(organization, slideRequestDtoList);
    }

    @Override
    public OrganizationAllDto update(OrganizationAllDto dto, Long id) {
        Optional<Organization> optional = organizationRepository.findById(id);
        if (optional.isPresent()) {
            return organizationMapper.organizationEntity2DtoAll(organizationRepository.save(organizationMapper.updateValues(dto, optional.get())));
        } else throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
    }

    @Override
    public Organization getOrganizationId(Long id) {
        Optional<Organization> organizationOp = organizationRepository.findById(id);
        if (organizationOp.isEmpty()) {
            throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
        }
        return organizationOp.get();
    }
}
