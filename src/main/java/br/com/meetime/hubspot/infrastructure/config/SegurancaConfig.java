package br.com.meetime.hubspot.infrastructure.config;

import br.com.meetime.hubspot.infrastructure.filter.WebhookAssinaturaFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SegurancaConfig {

    private final WebhookAssinaturaFilter webhookAssinaturaFilter;

    @Bean
    public SecurityFilterChain conjuntoFiltroSeguranca(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/oauth/**").permitAll()
                        .requestMatchers("/v1/contato/**").permitAll()
                        .requestMatchers("/v1/eventos/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger",
                                "/swagger.json"
                        ).permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(webhookAssinaturaFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}