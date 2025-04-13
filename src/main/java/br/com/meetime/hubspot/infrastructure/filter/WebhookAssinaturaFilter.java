package br.com.meetime.hubspot.infrastructure.filter;

import br.com.meetime.hubspot.infrastructure.Cache.CachedBodyRequest;
import br.com.meetime.hubspot.infrastructure.properties.HubspotProperties;
import br.com.meetime.hubspot.infrastructure.utils.HmacUtils;
import br.com.meetime.hubspot.infrastructure.utils.UrlUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebhookAssinaturaFilter extends OncePerRequestFilter {

    private final HubspotProperties hubspotProperties;
    private static final Duration DESVIO_PERMITIDO = Duration.ofMinutes(5);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Iniciando filtro de assinatura para a URL: {}", request.getRequestURI());

        if (request.getRequestURI().startsWith("/v1/eventos/contato-criado")) {
            String signature = request.getHeader("X-HubSpot-Signature-v3");
            String timestampHeader = request.getHeader("X-HubSpot-Request-Timestamp");
            log.debug("Headers de validação recebidos: Assinatura: {}, Timestamp: {}", signature, timestampHeader);

            if (signature == null || timestampHeader == null) {
                log.warn("Assinatura ou timestamp ausente nos cabeçalhos da requisição.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Assinatura do evento ausente");
                return;
            }

            long timestamp = Long.parseLong(timestampHeader);
            long now = Instant.now().toEpochMilli();
            log.debug("Timestamp recebido: {}, Timestamp atual: {}", timestamp, now);

            if (Math.abs(now - timestamp) > DESVIO_PERMITIDO.getSeconds()) {
                log.warn("Timestamp inválido. Diferença excede o desvio permitido de {} segundos.", DESVIO_PERMITIDO.getSeconds());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Registro de data e hora inválida");
                return;
            }

            CachedBodyRequest wrappedRequest = new CachedBodyRequest(request);
            String method = wrappedRequest.getMethod();
            String decodedUrl = UrlUtils.decodificarUrl(wrappedRequest.getRequestURL().toString());;
            String body = wrappedRequest.getRequestBody();

            log.debug("Método: {}, URL decodificada: {}, Corpo da requisição: {}", method, decodedUrl, body);

            String canonicalString = method + decodedUrl + body + timestampHeader;
            log.debug("String canônica gerada: {}", canonicalString);

            String expectedSignature = HmacUtils.gerarHmacBase64(canonicalString, hubspotProperties.getClientSecret());
            log.debug("Assinatura esperada: {}", expectedSignature);

            if (!MessageDigest.isEqual(expectedSignature.getBytes(), signature.getBytes())) {
                log.warn("Assinatura inválida. Assinatura recebida: {}", signature);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Assinatura do evento inválida");
                return;
            }

            log.info("Assinatura validada com sucesso.");
            filterChain.doFilter(wrappedRequest, response);
        } else {
            log.debug("URL não corresponde ao filtro. Continuando a cadeia de filtros.");
            filterChain.doFilter(request, response);
        }
    }
}
