package br.com.meetime.hubspot.application.port.input;

import br.com.meetime.hubspot.domain.model.OAuthTokenResponse;

public interface AccessTokenInputPort {
    OAuthTokenResponse getToken(String clientId, String clientSecret, String redirectUri, String code);
}
