package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberRequestDto;
import com.alkemy.ong.model.entity.Member;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class MemberMapper {

    public Member memberDto2Entity(MemberRequestDto memberDto) {
          Member member = new Member();
          member.setName(memberDto.getName());
          member.setImage(memberDto.getImage());
          member.setDescription(memberDto.getDescription());
          member.setTimestamps(new Timestamp(System.currentTimeMillis()));
          return  member;
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
}
