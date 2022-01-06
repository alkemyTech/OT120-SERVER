package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationAllDto;
import com.alkemy.ong.service.abstraction.IOrganizationService ;

@RestController
@RequestMapping("organization/public")
public class OrganizationController {

  @Autowired
  private IOrganizationService organizationService;

  @GetMapping("/{id}")
  public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable Long id) {
    OrganizationDto organization = organizationService.getById(id);
    return ResponseEntity.ok(organization);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrganizationAllDto> update(@Valid @RequestBody OrganizationAllDto dto, @PathVariable Long id){
    OrganizationAllDto organization = organizationService.update(dto,id);
            return ResponseEntity.ok(organization);
  }

}
