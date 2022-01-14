package com.alkemy.ong.dto.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.entity.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ContactDto contactToContactDto(Contact entity){
        return modelMapper.map(entity, ContactDto.class);
    }

    public Contact contactDtoToContact(ContactDto dto){
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        return contact;
    }
}
