package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.service.abstraction.IOrganizationService ;

@RestController
@RequestMapping("organization/public")
public class OrganizationController {

  @Autowired
  private IOrganizationService organizationService;	

  @GetMapping("/{id}")
  public ResponseEntity<OrganizationDto> getOrganizationById(@Valid @PathVariable Long id) {
    OrganizationDto organization = organizationService.getById(id);	
    return ResponseEntity.ok(organization);
  }

}
