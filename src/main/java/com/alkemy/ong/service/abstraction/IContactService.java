package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.entity.Contact;

import java.io.IOException;

public interface IContactService {

    Contact save(ContactDto contactDto) throws IOException;
}
