package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encodePassword;

    public UsersResponseDto usersDtoResponse (User entity){
        return modelMapper.map(entity, UsersResponseDto.class);
    }
  


    public User userDtoToEntity (UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(encodePassword.encode(userDto.getPassword()));

        return user;
    }

    public UserDto entityToUserDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;

    }
}
