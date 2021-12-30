package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactServiceImpl implements IContactService {


    @Override
    public Contact save(ContactDto contactDto) throws IOException {
        return null;
    }
}
