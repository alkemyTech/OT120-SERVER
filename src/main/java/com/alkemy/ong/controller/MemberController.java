package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.exception.FieldInvalidException;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.service.abstraction.IMembersService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MemberController {

    @Autowired
    private IMembersService membersService;

    @DeleteMapping(value = "/members/{id}")
    public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
        membersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/members")
    public ResponseEntity<MemberRequestDto> create(@RequestBody MemberRequestDto memberDto) throws FieldInvalidException {
        MemberRequestDto memberSave = membersService.save(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSave);
    }

    @GetMapping(value = "/members")
    public ResponseEntity<List<MemberDto>> getAllMember() {
        return new ResponseEntity(membersService.getAllMember(), HttpStatus.OK);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberDto> update(@RequestBody MemberDto member, @PathVariable Long id) {
        MemberDto memberUpdated = membersService.update(member, id);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdated);
    }

    @GetMapping(value = "/membersPaged")
    public ResponseEntity<Page<MemberDto>> getAllMembersPaged(@PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<MemberDto> result = membersService.findAll(pageable);

        return new ResponseEntity(result, HttpStatus.OK);
    }

}
