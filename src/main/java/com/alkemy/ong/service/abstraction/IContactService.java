package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.ParamNotFound;

public interface IContactService {

    ContactDto save(ContactDto contactDto) throws ParamNotFound;

}
