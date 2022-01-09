package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.service.MemberServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IMembersService {

  void delete(Long id) throws EntityNotFoundException;

  MemberRequestDto save(MemberRequestDto memberDto);

  List<MemberDto> getAllMember();

  MemberDto update(MemberDto member, Long id);

  Page<MemberDto> findAll(Pageable pageable);
}
