package com.alkemy.ong.service.abstraction;


import com.alkemy.ong.dto.UserDTO;

public interface UserService {

    //Update
    UserDTO update(Long id, UserDTO userDTO);

}

