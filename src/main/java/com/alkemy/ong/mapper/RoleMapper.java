package com.alkemy.ong.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    @Autowired
    private UserMapper userMapper;

    public RoleDto roleEntity2DTO(Role entity, boolean loadUsers){
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTimestamp(entity.getTimestamp());
        if(loadUsers){
            List<UserDto> usersDTOS = this.userMapper.userEntityList2DTOList(entity.getUsers(), false);

            dto.setUsers(usersDTOS);
        }
        return dto;
    }

    public List<RoleDto> roleEntitySet2DtoList(Collection<Role> entities, boolean loadUsers){
        List<RoleDto> dtos = new ArrayList<>();
        for(Role entity : entities){
            dtos.add(this.roleEntity2DTO(entity, false));
        }
        return dtos;
    }


}


