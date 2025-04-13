package br.com.meetime.hubspot.application.usecase;

import br.com.meetime.hubspot.application.port.input.CriarContatoInputPort;
import br.com.meetime.hubspot.application.port.output.CriarContatoOutputPort;
import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import br.com.meetime.hubspot.infrastructure.handler.ControleTaxaRetentativaHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CriarContatoUseCase implements CriarContatoInputPort {

    private final CriarContatoOutputPort criarContatoOutputPort;
    private final ControleTaxaRetentativaHandler controleTaxa;

    @Override
    public CriarContatoResponseV1 criarContatoV1(CriarContatoRequestV1 criarContatoRequest) {
        log.info("Iniciando o processo de criação de contato com a requisição: {}", criarContatoRequest);
        CriarContatoResponseV1 response = controleTaxa.executarLimiteTaxa(() -> criarContatoOutputPort.addContatoV1(criarContatoRequest));
        log.info("Contato criado com sucesso: {}", response);
        return response;
    }
}
