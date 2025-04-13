package br.com.meetime.hubspot.infrastructure.contract;

import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import br.com.meetime.hubspot.domain.model.response.OAuthTokenResponseV1;
import br.com.meetime.hubspot.domain.model.response.OAuthUrlResponseV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

public interface OAuthContract {
    @Operation(
            summary = "Obter URL de autorização",
            description = "Gera a URL de autorização para autenticação OAuth.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "URL criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = OAuthUrlResponseV1.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação nos dados fornecidos",
                            content = @Content(
                                    schema = @Schema(implementation = ApiErroResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(
                                    schema = @Schema(implementation = ApiErroResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<OAuthUrlResponseV1> getUrlAutorizacao();

    @Operation(
            summary = "Obter token de acesso",
            description = "Obtém o token de acesso usando o código de autorização.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "code",
                            description = "Código de autorização recebido após a autenticação",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "URL criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = OAuthUrlResponseV1.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação nos dados fornecidos",
                            content = @Content(
                                    schema = @Schema(implementation = ApiErroResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(
                                    schema = @Schema(implementation = ApiErroResponse.class)
                            )
                    )
            }
    )
    ResponseEntity<OAuthTokenResponseV1> getAccessToken(
            @NotBlank(message = "O código de autorização não pode ser nulo ou vazio")
            String code
    );
}
