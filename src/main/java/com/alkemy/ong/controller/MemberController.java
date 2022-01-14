package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberPageDto;
import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.service.abstraction.IMembersService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Size;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMembersService membersService;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        membersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<MemberRequestDto> create(@RequestBody MemberRequestDto memberDto) throws FieldInvalidException {
        MemberRequestDto memberSave = membersService.save(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSave);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMember() {
        return new ResponseEntity(membersService.getAllMember(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> update(@RequestBody MemberDto member, @PathVariable Long id) {
        MemberDto memberUpdated = membersService.update(member, id);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdated);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<MemberPageDto<MemberDto>> getPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer sizePage,
            @RequestParam(defaultValue = "id") String sortBy) throws NotFoundException {
        return new ResponseEntity<>(membersService.getPage(page, sizePage, sortBy), HttpStatus.OK);

    }

}
