package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.IMembersService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMembersService {

  private static final String MEMBER_NOT_FOUND_MESSAGE = "Member not found.";

  @Autowired
  private IMemberRepository memberRepository;

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    Member member = getMember(id);
    member.setSoftDelete(true);
    memberRepository.save(member);
  }

  private Member getMember(Long id) {
    Optional<Member> memberOptional = memberRepository.findById(id);
    if (memberOptional.isEmpty() || memberOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(MEMBER_NOT_FOUND_MESSAGE);
    }
    return memberOptional.get();
  }

  @Override
  public MemberRequestDto save(MemberRequestDto memberDto) {
    Member member = memberMapper.memberDto2Entity(memberDto);
    Member memberSaved = memberRepository.save(member);
    MemberRequestDto resul = memberMapper.memberEntity2Dto(memberSaved);
    return resul;
  }

}
