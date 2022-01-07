package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.IMembersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMembersService {

  private static final String MEMBER_NOT_FOUND_MESSAGE = "Member not found.";
  private static final String MEMBER_LIST_EMPTY_MESSAGE = "There are no members yet.";

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

  @Override
  public List<MemberDto> getAllMember() {
    List<MemberDto> members = memberRepository.findAll().stream().map(memberMapper::memberToDto).collect(Collectors.toList());
    if(members.isEmpty()){
      throw new EmptyListException(MEMBER_LIST_EMPTY_MESSAGE);
    }
    return members;
  }

  private Member getMember(Long id) {
    Optional<Member> memberOptional = memberRepository.findById(id);
    if (memberOptional.isEmpty() || memberOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(MEMBER_NOT_FOUND_MESSAGE);
    }
    return memberOptional.get();
  }

  @Override
  public MemberDto update(MemberDto memberDto, Long id){
    Optional<Member> memberOptional = memberRepository.findById(id);
    if(!memberOptional.isPresent()){
      throw new EntityNotFoundException("Member not found");
    }
    memberMapper.memberEntityUpdate(memberOptional.get(), memberDto);
    Member memberUpdated = memberRepository.save(memberOptional.get());
    MemberDto result = memberMapper.memberToDto(memberUpdated);
    return result;
  }


}
