package com.alkemy.ong.service;


import com.alkemy.ong.dto.MemberPageDto;
import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.IMembersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
  public MemberRequestDto save(MemberRequestDto memberDto) {
    Member member = memberMapper.memberDto2Entity(memberDto);
    Member memberSaved = memberRepository.save(member);
    MemberRequestDto resul = memberMapper.memberEntity2Dto(memberSaved);
    return resul;
  }

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

  @Override
  public MemberPageDto<MemberDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException {
    Pageable pageable = PageRequest.of(page, sizePage, Sort.by(sortBy));
    Page<Member> pageRecovered = memberRepository.findAll(pageable);
    Integer totalPages = pageRecovered.getTotalPages();
    Long totalElements = pageRecovered.getTotalElements();

    if (totalPages < page) {
      throw new NotFoundException("The page does not exists");
    }
    return memberMapper.toPageDto(pageRecovered, page,  totalElements, totalPages);
  }

}
