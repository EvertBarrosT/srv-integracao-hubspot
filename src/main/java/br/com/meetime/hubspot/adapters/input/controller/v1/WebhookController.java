package br.com.meetime.hubspot.adapters.input.controller.v1;

import br.com.meetime.hubspot.application.usecase.ContatoCriadoEventoUseCase;
import br.com.meetime.hubspot.domain.model.WebhookEvento;
import br.com.meetime.hubspot.infrastructure.contract.WebhookContract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/eventos")
@RequiredArgsConstructor
@Slf4j
public class WebhookController implements WebhookContract {

    private final ContatoCriadoEventoUseCase contatoCriadoEventoUseCase;

    @Override
    @PostMapping("/contato-criado")
    public ResponseEntity<Void> manipularEvento(@RequestBody List<WebhookEvento> eventos) {
        log.info("Recebido evento de webhook: {}", eventos);

        for (WebhookEvento evento : eventos) {
            contatoCriadoEventoUseCase.processarEvento(evento);
        }
        return ResponseEntity.ok().build();
    }
}
