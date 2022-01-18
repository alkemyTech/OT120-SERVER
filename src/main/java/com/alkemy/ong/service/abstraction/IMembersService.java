package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.MemberPageDto;
import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.dto.MemberDto;
import javassist.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IMembersService {

    void delete(Long id) throws EntityNotFoundException;

    MemberRequestDto save(MemberRequestDto memberDto);

    List<MemberDto> getAllMember();

    MemberDto update(MemberDto member, Long id);

    MemberPageDto<MemberDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException;
}
