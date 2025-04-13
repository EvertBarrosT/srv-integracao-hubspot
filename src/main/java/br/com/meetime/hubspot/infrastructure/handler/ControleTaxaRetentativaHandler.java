package br.com.meetime.hubspot.infrastructure.handler;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Slf4j
public class ControleTaxaRetentativaHandler {

    private final RateLimiter limitadorTaxa = RateLimiter.create(10.0);

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public <T> T executarLimiteTaxa(Supplier<T> action) {
        log.info("Tentando executar ação com controle de taxa e retentativa.");
        limitadorTaxa.acquire();
        log.debug("Permissão adquirida do limitador de taxa.");

        try {
            T result = action.get();
            log.info("Ação executada com sucesso.");
            return result;
        } catch (Exception e) {
            log.error("Erro ao executar ação: {}", e.getMessage(), e);
            throw e;
        }
    }
}
