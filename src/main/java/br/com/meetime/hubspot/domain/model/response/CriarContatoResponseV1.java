package br.com.meetime.hubspot.domain.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Resposta da criação de um contato")
public class CriarContatoResponseV1 {

    @JsonAlias("id")
    @Schema(description = "ID do contato", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @JsonAlias("archived")
    @Schema(description = "Indica se o contato está arquivado", example = "false")
    private boolean arquivado;

    @JsonAlias("createdAt")
    @Schema(description = "Data de criação do contato", example = "2023-01-01T12:00:00Z")
    private OffsetDateTime criadoEm;

    @JsonAlias("updatedAt")
    @Schema(description = "Data de atualização do contato", example = "2023-01-02T12:00:00Z")
    private OffsetDateTime atualizadoEm;

    @JsonAlias("archivedAt")
    @Schema(description = "Data de arquivamento do contato", example = "2023-01-03T12:00:00Z")
    private OffsetDateTime arquivadoEm;

    @JsonAlias("objectWriteTraceId")
    @Schema(description = "ID de rastreamento do objeto", example = "trace123")
    private String idRastreamento;

    @JsonAlias("properties")
    @Schema(description = "Propriedades do contato")
    private Properties properties;

    @JsonAlias("propertiesWithHistory")
    @Schema(description = "Histórico das propriedades do contato")
    private List<PropertyHistory> historicoPropriedades;

    @Data
    @Schema(description = "Histórico de uma propriedade do contato")
    public static class PropertyHistory {
        @JsonAlias("sourceId")
        @Schema(description = "ID da origem da propriedade", example = "source123")
        private String idOrigem;

        @JsonAlias("sourceType")
        @Schema(description = "Tipo da origem da propriedade", example = "API")
        private String tipoOrigem;

        @JsonAlias("sourceLabel")
        @Schema(description = "Rótulo da origem da propriedade", example = "label123")
        private String seloOrigem;

        @JsonAlias("updatedByUserId")
        @Schema(description = "ID do usuário que atualizou a propriedade", example = "67890")
        private Long idAtualizadoPorUsuario;

        @JsonAlias("value")
        @Schema(description = "Valor da propriedade", example = "valor123")
        private String valor;

        @JsonAlias("timestamp")
        @Schema(description = "Data e hora da atualização", example = "2023-01-02T12:00:00Z")
        private OffsetDateTime dataHora;
    }

    @Schema(description = "Propriedades do contato")
    @Data
    public static class Properties {
        @NotBlank(message = "Propriedade 'email' não pode ser nula ou vazia")
        @Schema(description = "Email do contato", example = "exemplo@dominio.com", requiredMode = Schema.RequiredMode.REQUIRED)
        private String email;

        @JsonAlias("firstname")
        @Schema(description = "Primeiro nome do contato", example = "João")
        private String primeironome;

        @JsonAlias("lastname")
        @Schema(description = "Último nome do contato", example = "Silva")
        private String ultimonome;
    }
}
