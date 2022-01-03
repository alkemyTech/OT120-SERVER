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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


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
    private TemplateEngine templateEngine;

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


    private String preparedWelcomeBodyEmail(UserDtoRequest user){

        Context context = new Context();

        context.setVariable("titleContent", MailMessage.REGISTER_TITLE.getValue());
        context.setVariable("textContent", MailMessage.getWelcomeMsg(user.getFirstName(), user.getLastName()));
        context.setVariable("facebookContact", MailMessage.CONTACT_FACEBOOK.getValue());
        context.setVariable("instagramContact", MailMessage.CONTACT_INSTAGRAM.getValue());
        context.setVariable("emailContact", MailMessage.CONTACT_MAIL.getValue());
        context.setVariable("phoneContact", MailMessage.CONTACT_PHONE.getValue());
        //context.setVariable("imageResourceName", MailMessage.WELCOME_IMAGE.getValue());

        return templateEngine.process("plantilla_email.html", context);

    }

    @Override
    public Response sendWelcomeEmail(UserDtoRequest user) {
        String mailBody = preparedWelcomeBodyEmail(user);
        Content content = new Content(TEXT_HTML, mailBody);
        Response response = sendEmail(user.getEmail(), MailMessage.WELCOME_SUBJECT.getValue(), content);
        return response;
    }





}
