package br.com.meetime.hubspot.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hubspot")
public class HubspotProperties {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
}
