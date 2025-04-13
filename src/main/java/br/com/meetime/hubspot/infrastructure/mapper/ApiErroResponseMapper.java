package br.com.meetime.hubspot.infrastructure.mapper;

import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.infrastructure.client.dto.HubspotDetalheErro;
import br.com.meetime.hubspot.infrastructure.client.dto.HubspotErroResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ApiErroResponseMapper {

    @Mappings({
            @Mapping(source = "category", target = "erro"),
            @Mapping(source = "message", target = "mensagem"),
            @Mapping(source = "correlationId", target = "correlationId"),
            @Mapping(target = "detalhes", ignore = true)
    })
    ApiErroResponse hubspotErroResponsetoApiError(HubspotErroResponse hubspot);

    @AfterMapping
    default void after(HubspotErroResponse hubspot, @MappingTarget ApiErroResponse response) {
        List<String> detalhes = Optional.ofNullable(hubspot.errors())
                .orElse(List.of())
                .stream()
                .map(this::formatarDetralhesErro)
                .collect(Collectors.toList());

        response.setDetalhes(detalhes);
    }

    default String formatarDetralhesErro(HubspotDetalheErro detail) {
        String field = detail.in() != null ? detail.in() : "unknown";
        return "[" + field + "] " + detail.message();
    }
}
