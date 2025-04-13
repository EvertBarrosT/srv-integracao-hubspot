package br.com.meetime.hubspot.infrastructure.Cache;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CachedBodyRequest extends HttpServletRequestWrapper {

    private final byte[] cachedBody;

    public CachedBodyRequest(HttpServletRequest request) throws IOException {
        super(request);
        this.cachedBody = request.getInputStream().readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() {
        log.info("Obtendo o InputStream da requisição em cache.");
        return new ServletInputStream() {
            private final ByteArrayInputStream buffer = new ByteArrayInputStream(cachedBody);

            public int read() {
                return buffer.read();
            }

            public boolean isFinished() {
                return buffer.available() == 0;
            }

            public boolean isReady() {
                log.debug("InputStream está pronto para leitura.");
                return true;
            }

            public void setReadListener(ReadListener listener) {
                log.debug("SetReadListener chamado, mas não implementado.");
            }
        };
    }

    public String getRequestBody() {
        String body = new String(cachedBody, StandardCharsets.UTF_8);
        log.info("Obtendo o corpo da requisição em cache como String. Tamanho: {} caracteres", body.length());
        return body;
    }
}
