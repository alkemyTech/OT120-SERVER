package com.alkemy.ong.service.abstraction;

import com.sendgrid.Response;

public interface IEmailService {

    Response sendEmail(String email, String senderEmail, String contentStr, String subject);
}
