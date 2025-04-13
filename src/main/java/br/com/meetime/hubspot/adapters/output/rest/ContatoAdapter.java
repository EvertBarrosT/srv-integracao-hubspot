package br.com.meetime.hubspot.adapters.output.rest;

import br.com.meetime.hubspot.application.port.output.CriarContatoOutputPort;
import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import br.com.meetime.hubspot.infrastructure.client.HubspotContatoFeignClient;
import br.com.meetime.hubspot.infrastructure.mapper.HubspotCriarContactRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContatoAdapter implements CriarContatoOutputPort {

    private final HubspotContatoFeignClient hubspotContatoFeignClient;

    private final HubspotCriarContactRequestMapper hubspotCriarContactRequestMapper;

    @Override
    public CriarContatoResponseV1 addContatoV1(CriarContatoRequestV1 criarContatoRequest) {
        return hubspotContatoFeignClient.criarContato(hubspotCriarContactRequestMapper.toHubspotCriarContatoRequest(criarContatoRequest));
    }
}
