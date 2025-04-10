package br.com.meetime.hubspot.infrastructure.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CorrelationIdUtils {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    public String getCorrelationId(String existingId) {
        String correlationId = StringUtils.isNotBlank(existingId)
                ? existingId
                : UUID.randomUUID().toString();

        MDC.put(CORRELATION_ID_HEADER, correlationId);
        return correlationId;
    }

    public void clear() {
        MDC.remove(CORRELATION_ID_HEADER);
    }

    public static String getHeaderName() {
        return CORRELATION_ID_HEADER;
    }
}
