package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.model.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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


    public List<MemberDto> memberEntities2membersDto(List<Member> members) {
        List<MemberDto> membersDTO = new ArrayList<>();
        for (Member member : members) {
            membersDTO.add(this.memberToDto(member));
        }
        return membersDTO;
    }
}