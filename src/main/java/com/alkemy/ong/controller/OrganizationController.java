package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.model.request.OrganizationDto;
import com.alkemy.ong.service.abstraction.IDeleteOrganizationService;
import com.alkemy.ong.service.abstraction.IOrganizationDtoService;

@RestController
@RequestMapping("organization/public")
public class OrganizationController {

  @Autowired
  private IOrganizationDtoService dtoService;	

  @Autowired
  private IDeleteOrganizationService softDeleteService;

  @GetMapping("/{id}")
  public ResponseEntity<OrganizationDto> getOrganizationById(@Valid @PathVariable Long id) {
    OrganizationDto organization = this.dtoService.getById(id);	
    return ResponseEntity.ok(organization);
  }

  @DeleteMapping
  public ResponseEntity<OrganizationDto> delete(@Valid @PathVariable Long id) {
    this.softDeleteService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
