package br.com.meetime.hubspot.domain.model.request;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Dados para criação de um contato")
public record CriarContatoRequestV1(

        @Valid
        @Schema(description = "Lista de associações do contato", requiredMode = Schema.RequiredMode.REQUIRED)
        List<Associacoes> associacoes,

        @Valid
        @Schema(description = "Propriedades do contato", requiredMode = Schema.RequiredMode.REQUIRED)
        Propriedades propriedades
) {
    @Schema(description = "Associações do contato")
    public record Associacoes(
            @NotNull(message = "Tipos de associação não podem ser nulos")
            @Schema(description = "Lista de tipos de associação", requiredMode = Schema.RequiredMode.REQUIRED)
            List<Type> tipos,

            @NotNull(message = "Associacao não pode ser nula")
            @Schema(description = "Informações da associação", requiredMode = Schema.RequiredMode.REQUIRED)
            To para
    ) {
        public record Type(
                @NotBlank(message = "Categoria da associação não pode ser nulo ou vazio")
                @Schema(description = "Categoria da associação", example = "parent", requiredMode = Schema.RequiredMode.REQUIRED)
                String categoriaAssociacao,

                @NotNull(message = "ID do tipo de associação não pode ser nulo ou vazio")
                @Schema(description = "ID do tipo de associação", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
                int idTipoAssociacao
        ) {
        }

        @Schema(description = "Detalhes da associação")
        public record To(
                @NotBlank(message = "ID da associação não pode ser nulo ou vazio")
                @Schema(description = "ID da associação", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
                String id
        ) {
        }
    }

    @Schema(description = "Propriedades do contato")
    public record Propriedades(
            @NotBlank(message = "Propriedade 'email' não pode ser nula ou vazia")
            @Schema(description = "Email do contato", example = "exemplo@dominio.com", requiredMode = Schema.RequiredMode.REQUIRED)
            String email,

            @Schema(description = "Primeiro nome do contato", example = "João")
            String primeiroNome,

            @Schema(description = "Último nome do contato", example = "Silva")
            String ultimoNome
    ) {
    }
}