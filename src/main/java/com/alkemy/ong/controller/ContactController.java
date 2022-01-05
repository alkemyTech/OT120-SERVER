package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.abstraction.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;


    @PostMapping
    public ResponseEntity<ContactDto> save(@Valid @RequestBody ContactDto contactDto, BindingResult error) {
        if (error.hasFieldErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<ObjectError> errorList = error.getAllErrors();
            for (ObjectError e : errorList) {
                stringBuilder.append(e.getDefaultMessage());
            }
        }
        ContactDto newContact = contactService.save(contactDto);
        return new ResponseEntity<>(newContact, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ContactDto>> getContacts(){
        return new ResponseEntity<List<ContactDto>>(contactService.findAll(), HttpStatus.OK);

    }
}
