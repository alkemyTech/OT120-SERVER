package com.alkemy.ong.comm;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Value("${emailSettings.apiKey}")
    private String apiKey;

    @Bean
    public SendGrid sendGrid() {

        return new SendGrid(apiKey);
    }
}
