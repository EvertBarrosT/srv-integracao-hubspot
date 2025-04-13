package br.com.meetime.hubspot.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public OpenAPI hubspotIntegraçãoAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Integração HubSpot")
                        .description("Serviço para autorização, criação de contatos e recebimento de eventos da HubSpot")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Evert Barros Tavares")
                                .email("evertbarros@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Oficial HubSpot")
                        .url("https://developers.hubspot.com/docs/api/overview"));
    }
}
