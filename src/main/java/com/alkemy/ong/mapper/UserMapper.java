package com.alkemy.ong.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    @Lazy
    private RoleMapper roleMapper;

    //Entity to DTO
    public UserDto userEntity2Dto(User userEntity, boolean loadRoles){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setPhoto(userEntity.getPhoto());
        if(loadRoles){
            List<RoleDto> roleDtoList = this.roleMapper.roleEntitySet2DtoList(userEntity.getRoles());
            userDto.setRoles(roleDtoList);
        }
        userDto.setTimestamp(userEntity.getTimestamp());

        return userDto;
    }

    public void UserRefreshValues(User entity, UserDto dto){
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setTimestamp(new Timestamp(System.currentTimeMillis()));

    }
    public List<UserDto> userEntityList2DTOList(List<User> userList, Boolean loadRoles){
        List<UserDto> dtoList = new ArrayList<>();
        for(User entity : userList){
            dtoList.add(this.userEntity2Dto(entity, loadRoles));
        }
        return dtoList;
    }
}



