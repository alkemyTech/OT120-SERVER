package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    IContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll().stream().map(contactMapper::contactDtoToContact).collect(Collectors.toList());
    }
}
