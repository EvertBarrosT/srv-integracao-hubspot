package br.com.meetime.hubspot.infrastructure.config;

import br.com.meetime.hubspot.infrastructure.client.error.FeignErroDecoder;
import br.com.meetime.hubspot.infrastructure.mapper.ApiErroResponseMapper;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final ApiErroResponseMapper mapper;

    @Bean
    public ErrorDecoder erroDecoder() {
        return new FeignErroDecoder(mapper);
    }
}
