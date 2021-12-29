package com.alkemy.ong.service.abstraction;


import com.alkemy.ong.dto.ContactDto;

import java.util.List;

public interface IContactService {

    public List<ContactDto> findAll();
}
