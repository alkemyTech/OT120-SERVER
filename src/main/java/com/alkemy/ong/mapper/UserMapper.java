package com.alkemy.ong.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.dto.UserDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    @Lazy
    private RoleMapper roleMapper;

    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encodePassword;

    public UsersResponseDto usersDtoResponse (User entity){
        return modelMapper.map(entity, UsersResponseDto.class);
    }


    //Entity to DTO
    public UserDtoResponse userEntity2Dto(User userEntity, boolean loadRoles){
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(userEntity.getId());
        userDtoResponse.setFirstName(userEntity.getFirstName());
        userDtoResponse.setLastName(userEntity.getLastName());
        userDtoResponse.setEmail(userEntity.getEmail());
        userDtoResponse.setPassword(userEntity.getPassword());
        userDtoResponse.setPhoto(userEntity.getPhoto());
        if(loadRoles){
            List<RoleDto> roleDtoList = this.roleMapper.roleEntitySet2DtoList(userEntity.getRoles());
            userDtoResponse.setRoles(roleDtoList);
        }
        userDtoResponse.setTimestamp(userEntity.getTimestamp());

        return userDtoResponse;
    }

    public void UserRefreshValues(User entity, UserDtoRequest dto){
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setTimestamp(new Timestamp(System.currentTimeMillis()));

    }

    public List<UserDtoResponse> userEntityList2DTOList(List<User> userList, Boolean loadRoles){
        List<UserDtoResponse> dtoList = new ArrayList<>();
        userList.stream().map(entity -> dtoList.add(userEntity2Dto(entity, loadRoles)));
        return dtoList;
    }

    public User userDtoToEntity (UserDtoRequest userDtoRequest) {
        User user = new User();
        user.setEmail(userDtoRequest.getEmail());
        user.setFirstName(userDtoRequest.getFirstName());
        user.setLastName(userDtoRequest.getLastName());
        user.setPassword(encodePassword.encode(userDtoRequest.getPassword()));

        return user;
    }

}
