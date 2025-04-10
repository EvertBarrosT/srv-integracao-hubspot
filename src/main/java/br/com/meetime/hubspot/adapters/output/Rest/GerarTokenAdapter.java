package br.com.meetime.hubspot.adapters.output.Rest;

import br.com.meetime.hubspot.application.port.output.GerarTokenOutputPort;
import br.com.meetime.hubspot.domain.model.OAuthTokenResponse;
import br.com.meetime.hubspot.infrastructure.client.HubspotTokenFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GerarTokenAdapter implements GerarTokenOutputPort {

    private final HubspotTokenFeignClient tokenClient;

    @Override
    public OAuthTokenResponse gerarToken(String clientId, String clientSecret, String redirectUri, String code) {
        return tokenClient.trocarCode("authorization_code",
                clientId,
                clientSecret,
                redirectUri,
                code
        );
    }
}
