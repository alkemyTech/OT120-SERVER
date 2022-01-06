package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.service.abstraction.IMembersService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

  @Autowired
  private IMembersService MembersService;

  @DeleteMapping(value = "/members/{id}")
  public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
    MembersService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/members")
  public  ResponseEntity<List<MemberDto>> getAllMember(){
    return new ResponseEntity(MembersService.getAllMember(),HttpStatus.OK);
  }

}
