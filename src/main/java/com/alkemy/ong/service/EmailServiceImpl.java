package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.email.MailMessage;
import com.alkemy.ong.service.abstraction.IEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    SendGrid sendGrid;

    @Override
    public Response sendEmail(String email, String senderEmail, String contentStr, String subject) {

        Response response = new Response();

        try {

            Email fromEmail = new Email(senderEmail);
            Email toEmail = new Email(email);

            Content content = new Content("text/plain", contentStr);

            Mail mail = new Mail(fromEmail, subject, toEmail, content);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
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
    public Response sendContactRegisterEmail(ContactDto user) throws IOException {

        return null;
    }

}
