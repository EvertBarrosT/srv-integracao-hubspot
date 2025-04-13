package br.com.meetime.hubspot.adapters.input.controller.v1;

import br.com.meetime.hubspot.application.usecase.CriarContatoUseCase;
import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import br.com.meetime.hubspot.infrastructure.contract.ContatoContract;
import br.com.meetime.hubspot.infrastructure.handler.ExcecaoGlobalHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contato")
@RequiredArgsConstructor
@Slf4j
public class ContatoController implements ContatoContract {

    private final CriarContatoUseCase criarContatoUseCase;

    @Override
    @PostMapping(path = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CriarContatoResponseV1> criarContato(
            @Valid
            @RequestBody
            CriarContatoRequestV1 criarContatoRequest
    ) {
        log.info("Recebida requisição para criar contato: {}", criarContatoRequest);

        return ResponseEntity.created(null).body(criarContatoUseCase.criarContatoV1(criarContatoRequest));
    }
}
