package br.com.meetime.hubspot.application.port.output;

import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;

public interface CriarContatoOutputPort {
    CriarContatoResponseV1 addContatoV1(CriarContatoRequestV1 criarContatoRequest);
}
