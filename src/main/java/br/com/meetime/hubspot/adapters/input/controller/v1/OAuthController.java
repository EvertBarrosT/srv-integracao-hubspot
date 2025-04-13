package br.com.meetime.hubspot.adapters.input.controller.v1;

import br.com.meetime.hubspot.application.port.input.AccessTokenInputPort;
import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;
import br.com.meetime.hubspot.domain.model.response.OAuthUrlResponseV1;
import br.com.meetime.hubspot.infrastructure.contract.OAuthContract;
import br.com.meetime.hubspot.infrastructure.properties.HubspotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OAuthController implements OAuthContract {

    private final HubspotProperties hubspotProperties;

    private final AccessTokenInputPort accessTokenInputPort;

    @Override
    @GetMapping(path = "/url")
    public ResponseEntity<OAuthUrlResponseV1> getUrlAutorizacao() {
        log.info("Recebida requisição para obter URL de autorização.");

        String authorizationUrl = String.format(
                "https://app.hubspot.com/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s",
                hubspotProperties.getClientId(),
                hubspotProperties.getRedirectUri(),
                hubspotProperties.getScope()
        );
        return ResponseEntity.ok(new OAuthUrlResponseV1(authorizationUrl));
    }

    @Override
    @GetMapping(path = "/token")
    public ResponseEntity<OAuthTokenResponseV1> getAccessToken(
            @RequestParam String code
    ) {
        log.info("Recebida requisição para obter token de acesso com código: {}", code);

        OAuthTokenResponseV1 response = accessTokenInputPort.getTokenV1(
                hubspotProperties.getClientId(),
                hubspotProperties.getClientSecret(),
                hubspotProperties.getRedirectUri(),
                code
        );

        return ResponseEntity.ok().body(response);
    }
}
