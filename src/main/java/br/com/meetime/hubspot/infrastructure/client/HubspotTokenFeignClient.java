package br.com.meetime.hubspot.infrastructure.client;

import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;
import br.com.meetime.hubspot.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hubspotTokenFeignClient", url = "${hubspot.api.url}", configuration = FeignConfig.class)
public interface HubspotTokenFeignClient {

    @PostMapping(value = "/oauth/v1/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuthTokenResponseV1 trocarCode(@RequestParam("grant_type") String grantType,
                                    @RequestParam("client_id") String clientId,
                                    @RequestParam("client_secret") String clientSecret,
                                    @RequestParam("redirect_uri") String redirectUri,
                                    @RequestParam("code") String code);
}
