package br.com.meetime.hubspot.adapters.output.rest;

import br.com.meetime.hubspot.application.port.output.GerarTokenOutputPort;
import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;
import br.com.meetime.hubspot.infrastructure.client.HubspotTokenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GerarTokenAdapter implements GerarTokenOutputPort {

    private final HubspotTokenFeignClient tokenClient;

    @Override
    public OAuthTokenResponseV1 gerarTokenV1(String clientId, String clientSecret, String redirectUri, String code) {
        return tokenClient.trocarCode("authorization_code",
                clientId,
                clientSecret,
                redirectUri,
                code
        );
    }
}
