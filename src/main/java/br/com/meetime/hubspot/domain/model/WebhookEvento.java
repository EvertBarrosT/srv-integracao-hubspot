package br.com.meetime.hubspot.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Evento recebido via Webhook")
public record WebhookEvento(
        @Schema(description = "ID do aplicativo associado ao evento", example = "10663855", requiredMode = Schema.RequiredMode.REQUIRED)
        long appId,

        @Schema(description = "ID único do evento", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
        long eventId,

        @Schema(description = "ID da assinatura do webhook", example = "3450292", requiredMode = Schema.RequiredMode.REQUIRED)
        long subscriptionId,

        @Schema(description = "ID do portal associado ao evento", example = "49650209", requiredMode = Schema.RequiredMode.REQUIRED)
        long portalId,

        @Schema(description = "Timestamp do momento em que o evento ocorreu", example = "1744506971554", requiredMode = Schema.RequiredMode.REQUIRED)
        long occurredAt,

        @Schema(description = "Tipo de assinatura do evento", example = "contact.creation", requiredMode = Schema.RequiredMode.REQUIRED)
        String subscriptionType,

        @Schema(description = "Número da tentativa de entrega do evento", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
        int attemptNumber,

        @Schema(description = "ID do objeto relacionado ao evento", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
        long objectId,

        @Schema(description = "Fonte da alteração que gerou o evento", example = "CRM", requiredMode = Schema.RequiredMode.REQUIRED)
        String changeSource,

        @Schema(description = "Indicador da natureza da alteração", example = "NEW", requiredMode = Schema.RequiredMode.REQUIRED)
        String changeFlag
) {
}