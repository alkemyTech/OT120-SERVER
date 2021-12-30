package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
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

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll().stream().map(contactMapper::contactDtoToContact).collect(Collectors.toList());
    }

    public Contact save(ContactDto contactDto) throws IOException {
        return null;
    }
}
