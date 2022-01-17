package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.ParamNotFound;


import java.util.List;

public interface IContactService {

    List<ContactDto> findAll();

    ContactDto save(ContactDto contactDto) throws ParamNotFound;


}
