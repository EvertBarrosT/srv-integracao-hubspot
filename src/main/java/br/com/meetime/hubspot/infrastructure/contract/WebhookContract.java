package br.com.meetime.hubspot.infrastructure.contract;

import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.domain.model.WebhookEvento;
import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.response.CriarContatoResponseV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WebhookContract {
    @Operation(
            summary = "Receber evento de Webhook",
            description = "Recebe eventos de Webhook enviados pelo HubSpot.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER, name="X-HubSpot-Signature-V3", description="Cabeçalho que contém a assinatura HMAC gerada pelo HubSpot para validar a autenticidade da requisição.", required=true, schema = @Schema(type = "string")),
                    @Parameter(in = ParameterIn.HEADER, name="X-HubSpot-Request-Timestamp", description="Cabeçalho que contém o timestamp (em segundos) da requisição enviada pelo HubSpot.", required=true, schema = @Schema(type = "string"))
            },
            requestBody = @RequestBody(
                    description = "Lista de eventos",
                    required = true,
                    content = @Content(
                            schema = @Schema(type = "array", implementation = WebhookEvento.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Evento capturado com sucesso"
                    )
            }
    )
    ResponseEntity<Void> manipularEvento(
            @Valid
            @NotEmpty(message = "A lista de eventos não pode ser vazia")
            List<WebhookEvento> events
    );
}
