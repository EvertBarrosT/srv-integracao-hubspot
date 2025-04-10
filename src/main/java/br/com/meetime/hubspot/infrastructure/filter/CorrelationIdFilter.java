package br.com.meetime.hubspot.infrastructure.filter;

import br.com.meetime.hubspot.infrastructure.utils.CorrelationIdUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CorrelationIdFilter implements Filter {

    private final CorrelationIdUtils correlationIdUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.debug("Iniciando filtro de Correlation ID");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String idExistente = httpRequest.getHeader(CorrelationIdUtils.getHeaderName());
        String correlationId = correlationIdUtils.getCorrelationId(idExistente);

        httpResponse.setHeader(CorrelationIdUtils.getHeaderName(), correlationId);

        log.debug("Correlation ID adicionado: {}", correlationId);

        try {
            chain.doFilter(request, response);
        } finally {
            correlationIdUtils.clear();
            log.debug("Correlation ID Limpo");
        }
    }
}

