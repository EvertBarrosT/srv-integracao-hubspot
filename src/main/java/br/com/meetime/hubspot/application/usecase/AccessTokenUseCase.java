package br.com.meetime.hubspot.application.usecase;

import br.com.meetime.hubspot.application.port.input.AccessTokenInputPort;
import br.com.meetime.hubspot.application.port.output.GerarTokenOutputPort;
import br.com.meetime.hubspot.domain.model.OAuthTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccessTokenUseCase implements AccessTokenInputPort {

    private final GerarTokenOutputPort gerarTokenOutputPort;

    @Override
    public OAuthTokenResponse getToken(String clientId, String clientSecret, String redirectUri, String code) {
        log.info("Iniciando AccessTokenUseCase.getToken, Request= code: {}, clientSecret: {}", code, clientSecret);
        return gerarTokenOutputPort.gerarToken(clientId, clientSecret, redirectUri, code);
    }
}
