package com.alkemy.ong.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;


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

    @Override
    public OrganizationDto getById(Long id) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isEmpty() || organizationOptional.get().isSoftDelete()) {
            throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
        }
        return organizationMapper.organizationEntity2Dto(organizationOptional.get());
    }

    @Override
    public OrganizationAllDto update(OrganizationAllDto dto, Long id) throws Exception {
        Optional<Organization> optional = organizationRepository.findById(id);
        if (optional.isPresent()) {
            Organization organization = optional.get();
            if (organization.getName().isEmpty()) {
                throw new Exception("El nombre no puede estar vacío");
            }
            if (organization.getImage().isEmpty()) {
                throw new Exception("Debe agregar una imagen");
            }
            if (organization.getEmail().isEmpty()) {
                throw new Exception("El email no puede estar vacío");
            }
            if (organization.getWelcomeText().isEmpty()) {
                throw new Exception("El mensaje de bienvenida no puede estar vacío");
            }

            organization = organizationMapper.organizationDto2EntityAll(dto);
            organizationRepository.save(organization);


        } else {
            throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
        }

        return dto;

    }

}
