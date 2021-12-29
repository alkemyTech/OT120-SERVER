package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.entity.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    @Autowired
    ModelMapper modelMapper;

    public ContactDto contactDtoToContact (Contact entity){
        return modelMapper.map(entity, ContactDto.class);
    }
}
