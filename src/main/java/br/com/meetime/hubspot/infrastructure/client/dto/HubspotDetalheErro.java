package br.com.meetime.hubspot.infrastructure.client.dto;

import java.util.Map;

public record HubspotDetalheErro(
        String subCategory,
        String code,
        String in,
        String message,
        Map<String, Object> context
) {}
