package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.MemberDto;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IMembersService {

  void delete(Long id) throws EntityNotFoundException;
  List<MemberDto> getAllMember();
  MemberDto update(MemberDto member, Long id);
}
