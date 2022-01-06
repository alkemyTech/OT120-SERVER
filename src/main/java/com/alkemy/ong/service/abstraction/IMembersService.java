package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.MemberRequestDto;


import javax.persistence.EntityNotFoundException;

public interface IMembersService {

  void delete(Long id) throws EntityNotFoundException;

  MemberRequestDto save(MemberRequestDto memberDto);

}
