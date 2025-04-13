package br.com.meetime.hubspot.application.port.output;

import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;

public interface GerarTokenOutputPort {
    OAuthTokenResponseV1 gerarTokenV1(String clientId, String clientSecret, String redirectUri, String code);
}
