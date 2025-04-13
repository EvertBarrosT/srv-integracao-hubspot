package br.com.meetime.hubspot.infrastructure.contract;

import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Contato", description = "Gerenciamento de contatos no HubSpot")
public interface ContatoContract {
    @Operation(
            summary = "Criar um novo contato",
            description = "Cria um novo contato no HubSpot com os dados fornecidos.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER, name="Authorization", description="Token de autorização", required=true, schema = @Schema(type = "string"))
            },
            requestBody = @RequestBody(
                    description = "Dados necessários para criar um contato",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CriarContatoRequestV1.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Contato criado com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = CriarContatoResponseV1.class)
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
    ResponseEntity<CriarContatoResponseV1> criarContato(CriarContatoRequestV1 criarContatoRequest);
}
