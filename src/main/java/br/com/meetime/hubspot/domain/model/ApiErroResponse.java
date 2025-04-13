package br.com.meetime.hubspot.domain.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Detalhes do erro retornado pela API")
public class ApiErroResponse {

    @Schema(description = "Breve descrição do erro", example = "Forbidden", requiredMode = Schema.RequiredMode.REQUIRED)
    private String erro;

    @Schema(description = "Mensagem detalhada do erro", example = "Requisição inválida", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mensagem;

    @Schema(description = "ID de correlação para rastreamento", example = "abc123", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String correlationId;

    @ArraySchema(
            schema = @Schema(description = "Lista de detalhes adicionais sobre o erro", example = "[\"Campo X é obrigatório\", \"Formato inválido\"]"),
            minItems = 0
    )
    private List<String> detalhes;
}
