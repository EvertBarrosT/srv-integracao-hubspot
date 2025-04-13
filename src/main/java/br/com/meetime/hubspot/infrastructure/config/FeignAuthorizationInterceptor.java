package br.com.meetime.hubspot.infrastructure.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FeignAuthorizationInterceptor {

    private final ObjectFactory<HttpServletRequest> httpServletRequestFactory;

    @Bean
    public RequestInterceptor headerOAuthInterceptor() {
        return requestTemplate -> {
            log.info("Interceptando requisição para adicionar cabeçalho de autorização.");

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest httpServletRequest = attributes.getRequest();
                String authHeader = httpServletRequest.getHeader("Authorization");

                if (StringUtils.isNotBlank(authHeader)) {
                    log.debug("Cabeçalho de autorização encontrado: {}", authHeader);
                    requestTemplate.header("Authorization", authHeader);
                }
            }
        };
    }
}
