package com.alkemy.ong.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alkemy.ong.dto.RoleDTO;
import com.alkemy.ong.dto.UserDTO;
import com.alkemy.ong.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    @Autowired
    private UserMapper userMapper;

    public RoleDTO roleEntity2DTO(Role entity, boolean loadUsers){
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTimestamp(entity.getTimestamp());
        if(loadUsers){
            List<UserDTO> usersDTOS = this.userMapper.userEntityList2DTOList(entity.getUsers(), false);
            dto.setUsers(usersDTOS);
        }
        return dto;
    }

    public List<RoleDTO> roleEntitySet2DTOList(Collection<Role> entities, boolean loadUsers){
        List<RoleDTO> dtos = new ArrayList<>();
        for(Role entity : entities){
            dtos.add(this.roleEntity2DTO(entity, false));
        }
        return dtos;
    }

}


