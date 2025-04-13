package br.com.meetime.hubspot.domain.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Resposta com o token de acesso OAuth")
public record OAuthTokenResponseV1(
        @JsonAlias("access_token")
        @Schema(description = "Token de acesso", example = "abc123", requiredMode = Schema.RequiredMode.REQUIRED)
        String tokenAcesso,

        @JsonAlias("refresh_token")
        @Schema(description = "Token de atualização", example = "refresh123", requiredMode = Schema.RequiredMode.REQUIRED)
        String atualizarToken,

        @JsonAlias("expires_in")
        @Schema(description = "Tempo de expiração em segundos", example = "3600", requiredMode = Schema.RequiredMode.REQUIRED)
        long expiraEm,

        @JsonAlias("token_type")
        @Schema(description = "Tipo do token", example = "Bearer", requiredMode = Schema.RequiredMode.REQUIRED)
        String tipoToken,

        @Schema(description = "Escopo do token", example = "contacts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String escopo
) {}
