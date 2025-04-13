package br.com.meetime.hubspot.application.port.input;

import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;

public interface AccessTokenInputPort {
    OAuthTokenResponseV1 getTokenV1(String clientId, String clientSecret, String redirectUri, String code);
}
