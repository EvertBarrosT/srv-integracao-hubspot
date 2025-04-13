package br.com.meetime.hubspot.infrastructure.client.dto;

import java.util.List;
import java.util.Map;

public record HubspotErroResponse(
        String status,
        String category,
        String subCategory,
        String message,
        String correlationId,
        String requestId,
        Map<String, List<String>> context,
        Map<String, String> links,
        List<HubspotDetalheErro> errors
) {}
