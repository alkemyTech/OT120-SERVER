package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public ContactDto save(ContactDto contactDto) {
        if(contactDto.getName().trim().isEmpty() && contactDto.getEmail().trim().isEmpty()){
            throw new NullPointerException("El nombre y el email no pueden estar vacíos");
        }else if(contactDto.getName().trim().isEmpty()){
            throw new NullPointerException("El nombre no puede estar vacío");
        }else if(contactDto.getEmail().trim().isEmpty()){
            throw new NullPointerException("El email no puede estar vacío");
        }



        Contact contact = contactMapper.contactDtotoContact(contactDto);
        Contact contactSaved = contactRepository.save(contact);
        ContactDto result = contactMapper.contactToContactDto(contactSaved);
        return result;
    }

}
