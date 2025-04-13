package br.com.meetime.hubspot.domain.model.request;

import java.util.List;

public record HubspotCriarContatoRequest(
        List<Association> associations,
        Properties properties
) {
    public record Association(
            List<Type> types,
            To to
    ) {
        public record Type(
                String associationCategory,
                int associationTypeId
        ) {
        }

        public record To(
                String id
        ) {
        }
    }

    public record Properties(
            String email,
            String firstname,
            String lastname
    ) {
    }
}