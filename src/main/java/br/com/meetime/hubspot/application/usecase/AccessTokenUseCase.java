package br.com.meetime.hubspot.application.usecase;

import br.com.meetime.hubspot.application.port.input.AccessTokenInputPort;
import br.com.meetime.hubspot.application.port.output.GerarTokenOutputPort;
import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;
import br.com.meetime.hubspot.infrastructure.handler.ControleTaxaRetentativaHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccessTokenUseCase implements AccessTokenInputPort {

    private final GerarTokenOutputPort gerarTokenOutputPort;
    private final ControleTaxaRetentativaHandler controleTaxa;

    @Override
    public OAuthTokenResponseV1 getTokenV1(String clientId, String clientSecret, String redirectUri, String code) {
        log.info("Iniciando recuperação de token, Request= code: {}", code, clientSecret);
        OAuthTokenResponseV1 response = controleTaxa.executarLimiteTaxa(() -> gerarTokenOutputPort.gerarTokenV1(clientId, clientSecret, redirectUri, code));
        log.info("Token recuperado com sucesso, Response= {}", response);
        return response;
    }
}
