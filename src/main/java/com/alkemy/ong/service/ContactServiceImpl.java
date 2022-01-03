package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

@Service
public class ContactServiceImpl implements IContactService {

    public final String LISTA_VACIA = "La Lista se encuentra vacía";

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactDto> findAll() {
        if (contactRepository.findAll() == null) {
            throw new NotFoundExceptions(LISTA_VACIA);
        }
        return contactRepository.findAll().stream().map(contactMapper::contactDtoToContact).collect(Collectors.toList());
    }

    public Contact save(ContactDto contactDto) throws IOException {
        return null;
    }
}
