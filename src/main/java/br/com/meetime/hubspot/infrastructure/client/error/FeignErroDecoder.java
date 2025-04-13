package br.com.meetime.hubspot.infrastructure.client.error;

import br.com.meetime.hubspot.domain.exception.HubspotException;
import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.infrastructure.client.dto.HubspotErroResponse;
import br.com.meetime.hubspot.infrastructure.mapper.ApiErroResponseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FeignErroDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ErrorDecoder defaultDecoder = new Default();
    private final ApiErroResponseMapper mapper;

    public FeignErroDecoder(ApiErroResponseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();

        try {
            String body = response.body() != null
                    ? new String(response.body().asInputStream().readAllBytes())
                    : "";

            boolean bodyIsBlank = StringUtils.isBlank(body);

            if(status == 401 && bodyIsBlank) {
                return new HubspotException(401, new ApiErroResponse("INVALID_AUTHENTICATION", "Erro de autenticação", "Erro ao processar token de autenticação", List.of("Authentication credentials not found. This API supports OAuth 2.0 authentication and you can find more details at https://developers.hubspot.com/docs/methods/auth/oauth-overview")));
            }

            if (status >= 400 && status < 600 && !bodyIsBlank) {
                HubspotErroResponse hubspotErro = objectMapper.readValue(body, HubspotErroResponse.class);
                log.warn("Erro do Feign ({}): {}", methodKey, hubspotErro.message());

                return new HubspotException(status, mapper.hubspotErroResponsetoApiError(hubspotErro));
            }
        } catch (IOException e) {
            log.error("Erro ao processar corpo da resposta Feign", e);
            throw new HubspotException(500, new ApiErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro interno", "Erro ao processar resposta do Hubspot", Collections.emptyList()));
        }

        return defaultDecoder.decode(methodKey, response);
    }
}
