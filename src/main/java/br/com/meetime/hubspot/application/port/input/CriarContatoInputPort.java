package br.com.meetime.hubspot.application.port.input;

import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;

public interface CriarContatoInputPort {
    CriarContatoResponseV1 criarContatoV1(CriarContatoRequestV1 criarContatoRequest);
}
