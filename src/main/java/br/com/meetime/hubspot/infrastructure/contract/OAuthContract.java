package br.com.meetime.hubspot.infrastructure.contract;

import br.com.meetime.hubspot.domain.model.OAuthTokenResponse;
import br.com.meetime.hubspot.domain.model.OAuthUrlResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

public interface OAuthContract {

    ResponseEntity<OAuthUrlResponse> getUrlAutorizacao();


    ResponseEntity<OAuthTokenResponse> getAccessToken(
            @NotBlank(message = "O código de autorização não pode ser nulo ou vazio")
            String code
    );
}
