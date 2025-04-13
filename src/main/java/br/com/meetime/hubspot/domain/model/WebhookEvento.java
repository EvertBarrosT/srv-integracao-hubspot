package br.com.meetime.hubspot.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Evento recebido via Webhook")
public record WebhookEvento(
        @Schema(description = "ID do objeto relacionado ao evento", example = "12345", requiredMode = Schema.RequiredMode.REQUIRED)
        String objectId,

        @Schema(description = "ID único do evento", example = "event123", requiredMode = Schema.RequiredMode.REQUIRED)
        String eventId,

        @Schema(description = "ID da assinatura do webhook", example = "sub123", requiredMode = Schema.RequiredMode.REQUIRED)
        String subscriptionId,

        @Schema(description = "ID do portal associado ao evento", example = "portal123", requiredMode = Schema.RequiredMode.REQUIRED)
        String portalId,

        @Schema(description = "ID do aplicativo associado ao evento", example = "app123", requiredMode = Schema.RequiredMode.REQUIRED)
        String appId,

        @Schema(description = "Rótulo do evento", example = "label123", requiredMode = Schema.RequiredMode.REQUIRED)
        long label,

        @Schema(description = "Número da tentativa de entrega do evento", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        int attemptNumber,

        @Schema(description = "Fonte da alteração que gerou o evento", example = "API", requiredMode = Schema.RequiredMode.REQUIRED)
        String changeSource,

        @Schema(description = "Tipo de assinatura do evento", example = "contact.creation", requiredMode = Schema.RequiredMode.REQUIRED)
        String subscriptionType,

        @Schema(description = "Tipo de objeto relacionado ao evento", example = "contact", requiredMode = Schema.RequiredMode.REQUIRED)
        String objectTypeId,

        @Schema(description = "ID da origem do evento", example = "source123", requiredMode = Schema.RequiredMode.REQUIRED)
        String sourceId
) {
}