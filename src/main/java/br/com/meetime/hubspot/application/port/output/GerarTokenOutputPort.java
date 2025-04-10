package br.com.meetime.hubspot.application.port.output;

import br.com.meetime.hubspot.domain.model.OAuthTokenResponse;

public interface GerarTokenOutputPort {
    OAuthTokenResponse gerarToken(String clientId, String clientSecret, String redirectUri, String code);
}
