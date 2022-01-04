package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.enums.MailMessage;
import com.alkemy.ong.service.abstraction.IEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class EmailServiceImpl implements IEmailService {


        private final String endpoint = "mail/send";
        private static final String TEXT_HTML  = "text/html";

        @Value("${emailSettings.senderEmail}")
        private String senderEmail;

        @Autowired
        private Environment env;

        @Autowired
        SendGrid sendGrid;


    @Override
    public Response sendEmail(String email, String subject, Content content) {

        String apiKey = env.getProperty("SENDGRID_APIKEY");

        Response response = new Response();

        try {
            Email fromEmail = new Email(this.senderEmail);
            Email toEmail = new Email(email);
            Mail mail = new Mail(fromEmail, subject, toEmail, content);
            SendGrid sendGrid = new SendGrid(apiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint(this.endpoint);
            request.setBody(mail.build());
            response = sendGrid.api(request);
            if(response != null) {
                System.out.println("Response code from SendGrid " + response.getHeaders());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    @Override
    public Response sendWelcomeEmail(UserDtoRequest userRequestDto) {

        return null;
    }

    @Override
    public Response sendContactRegisterEmail(ContactDto contact){
        Content content = new Content("text/plain", MailMessage.GetRegisterContactMsg(contact.getName()));
        Response response = sendEmail(contact.getEmail(), "Gracias por tu registro! ", content);
        return response;
    }



}
