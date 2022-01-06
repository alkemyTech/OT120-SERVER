package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    EmailServiceImpl emailService;

    public final String LISTA_VACIA = "La Lista se encuentra vacía";

    @Override
    public ContactDto save(ContactDto contactDto) throws ParamNotFound {
        if (contactDto.getName().trim().isEmpty() && contactDto.getEmail().trim().isEmpty()) {
            throw new ParamNotFound("El nombre y el email no pueden estar vacíos");
        } else if (contactDto.getName().trim().isEmpty()) {
            throw new ParamNotFound("El nombre no puede estar vacío");
        } else if (contactDto.getEmail().trim().isEmpty()) {
            throw new ParamNotFound("El email no puede estar vacío");
        }

        Contact contact = contactMapper.contactDtoToContact(contactDto);
        Contact contactSaved = contactRepository.save(contact);
        ContactDto result = contactMapper.contactToContactDto(contactSaved);
        emailService.sendContactRegisterEmail(contactDto);
        return result;
    }

     @Override
    public List<ContactDto> findAll() {
        if (contactRepository.findAll() == null) {
            throw new NotFoundExceptions(LISTA_VACIA);
        }
        return contactRepository.findAll().stream().map(contactMapper::contactToContactDto).collect(Collectors.toList());
    }

}

