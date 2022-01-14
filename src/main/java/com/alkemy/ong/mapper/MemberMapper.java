package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.MemberPageDto;
import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.model.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class MemberMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MemberDto memberToDto(Member entity) {
        return modelMapper.map(entity, MemberDto.class);
    }

    public void memberEntityUpdate(Member member, MemberDto memberDto) {
        member.setName(memberDto.getName());
        member.setImage(memberDto.getImage());
        member.setDescription(memberDto.getDescription());
        member.setTimestamps(memberDto.getTimestamps());
    }

    public Member memberDto2Entity(MemberRequestDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setImage(memberDto.getImage());
        member.setDescription(memberDto.getDescription());
        member.setTimestamps(new Timestamp(System.currentTimeMillis()));
        return member;
    }

    public MemberRequestDto memberEntity2Dto(Member entity) {
        MemberRequestDto dto = new MemberRequestDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTimestamps(entity.getTimestamps());
        return dto;
    }

    private List<MemberDto> memberEntityList2memberDtoList(Page<Member> membersPage) {

        List<MemberDto> membersDto = new ArrayList<>();

        if (membersPage.hasContent()) {
            membersDto = membersPage.stream().map(member -> {
                return new MemberDto(member.getId(), member.getName(), member.getImage(), member.getImage(), member.getTimestamps());
            }).collect(Collectors.toList());
        }
        return membersDto;
    }

    public MemberPageDto<MemberDto> toPageDto(Page<Member> memberPage, Integer pageNumber, Integer totalPages) {

        MemberPageDto<MemberDto> pageDto = new MemberPageDto<>();

        pageDto.setTotalPages(totalPages);

        if (memberPage.hasNext()) {

            pageDto.setNextPage("/members/page?page=" + (pageNumber + 1));
        }

        if (memberPage.hasPrevious()) {

            pageDto.setPreviousPage("/members/page?page=" + (pageNumber - 1));
        }
        pageDto.setList(memberEntityList2memberDtoList((memberPage)));
        return pageDto;
    }
}