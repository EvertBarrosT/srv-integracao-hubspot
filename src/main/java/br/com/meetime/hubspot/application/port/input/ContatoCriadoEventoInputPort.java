package br.com.meetime.hubspot.application.port.input;

import br.com.meetime.hubspot.domain.model.WebhookEvento;

public interface ContatoCriadoEventoInputPort {
    void processarEvento(WebhookEvento evento);
}
