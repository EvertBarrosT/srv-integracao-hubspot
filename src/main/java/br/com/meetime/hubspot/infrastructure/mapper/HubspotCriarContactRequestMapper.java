package br.com.meetime.hubspot.infrastructure.mapper;

import br.com.meetime.hubspot.domain.model.request.CriarContatoRequestV1;
import br.com.meetime.hubspot.domain.model.request.HubspotCriarContatoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HubspotCriarContactRequestMapper {

    @Mapping(target = "associations", source = "associacoes")
    @Mapping(target = "properties.email", source = "propriedades.email")
    @Mapping(target = "properties.firstname", source = "propriedades.primeiroNome")
    @Mapping(target = "properties.lastname", source = "propriedades.ultimoNome")
    HubspotCriarContatoRequest toHubspotCriarContatoRequest(CriarContatoRequestV1 request);

    @Mapping(target = "types", source = "tipos")
    @Mapping(target = "to", source = "para")
    HubspotCriarContatoRequest.Association toAssociation(CriarContatoRequestV1.Associacoes associacoes);

    @Mapping(target = "associationCategory", source = "categoriaAssociacao")
    @Mapping(target = "associationTypeId", source = "idTipoAssociacao")
    HubspotCriarContatoRequest.Association.Type toType(CriarContatoRequestV1.Associacoes.Type type);

    @Mapping(target = "id", source = "id")
    HubspotCriarContatoRequest.Association.To toTo(CriarContatoRequestV1.Associacoes.To to);
}
