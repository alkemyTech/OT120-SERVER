package com.alkemy.ong.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.RoleDTO;
import com.alkemy.ong.dto.UserDTO;
import com.alkemy.ong.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private RoleMapper roleMapper;
    @Autowired
    public UserMapper(@Lazy RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }



    //Entity to DTO
    public UserDTO userEntity2DTO(User userEntity, boolean loadRoles){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setPhoto(userEntity.getPhoto());
        if(loadRoles){
            List<RoleDTO> rolDTOS = this.roleMapper.roleEntitySet2DTOList(userEntity.getRoles(), loadRoles);
            userDTO.setRoles(rolDTOS);
        }
        userDTO.setTimestamp(userEntity.getTimestamp());

        return userDTO;
    }

    public void UserRefreshValues(User entity, UserDTO dto){
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setTimestamp(new Timestamp(System.currentTimeMillis()));

    }
    public List<UserDTO> userEntityList2DTOList(List<User> userList, Boolean loadRoles){
        List<UserDTO> dtos = new ArrayList<>();
        for(User entity : userList){
            dtos.add(this.userEntity2DTO(entity, loadRoles));
        }
        return dtos;
    }
}



