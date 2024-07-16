package com.ian.davidson.port.scanner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swagger() {
        Contact contact = new Contact();
        contact.setEmail("ianmattdavidson@gmail.com");
        contact.setName("Ian Davidson");
        contact.setUrl("https://github.com/iandavidson");

        Info info = new Info().title("Port Scanner Application").version("0.0.1").contact(contact).description("Web " +
                "based application that can poll IPs and Ports");

        return new OpenAPI().info(info);
    }
}
