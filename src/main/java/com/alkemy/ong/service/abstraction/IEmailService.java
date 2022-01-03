package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ContactDto;
import com.sendgrid.Response;

import java.io.IOException;

public interface IEmailService {

    Response sendEmail(String email, String senderEmail, String contentStr, String subject);

    
}
