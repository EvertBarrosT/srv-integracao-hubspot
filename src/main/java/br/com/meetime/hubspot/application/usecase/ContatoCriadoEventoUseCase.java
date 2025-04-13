package br.com.meetime.hubspot.application.usecase;

import br.com.meetime.hubspot.application.port.input.ContatoCriadoEventoInputPort;
import br.com.meetime.hubspot.domain.model.WebhookEvento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContatoCriadoEventoUseCase implements ContatoCriadoEventoInputPort {
    @Override
    public void processarEvento(WebhookEvento evento) {
        log.info("🚀 Iniciando o processamento do evento de contato criado...");
        log.debug("📋 Detalhes do evento recebido: {}", evento);
        log.info("✅ Evento processado com sucesso! ID do Evento: {}", evento.eventId());
    }
}
