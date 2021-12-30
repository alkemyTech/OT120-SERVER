package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.model.entity.User;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Content;

import java.io.IOException;

public interface IEmailService {

    Response sendEmail(String email, String subject, Content content);

    Response sendWelcomeEmail(UserDtoRequest user);

    Response sendContactRegisterEmail(ContactDto contact);


}
