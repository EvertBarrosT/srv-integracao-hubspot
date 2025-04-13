package br.com.meetime.hubspot.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta com a URL de autorização OAuth")
public record OAuthUrlResponseV1(
        @Schema(description = "URL de autorização", example = "https://app.hubspot.com/oauth/authorize", requiredMode = Schema.RequiredMode.REQUIRED)
        String authorizationUrl
) {}
