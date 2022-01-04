package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.entity.Contact;
import java.io.IOException;
import java.util.List;

public interface IContactService {

    List<ContactDto> findAll();

    Contact save(ContactDto contactDto) throws IOException;

}
