package br.com.meetime.hubspot.infrastructure.client;

import br.com.meetime.hubspot.domain.model.request.HubspotCriarContatoRequest;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import br.com.meetime.hubspot.infrastructure.config.FeignAuthorizationInterceptor;
import br.com.meetime.hubspot.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hubspotContacts", url = "${hubspot.api.url}", configuration = {FeignConfig.class, FeignAuthorizationInterceptor.class})
public interface HubspotContatoFeignClient {

    @PostMapping(path = "/crm/v3/objects/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    CriarContatoResponseV1 criarContato(@RequestBody HubspotCriarContatoRequest requestBody);
}
