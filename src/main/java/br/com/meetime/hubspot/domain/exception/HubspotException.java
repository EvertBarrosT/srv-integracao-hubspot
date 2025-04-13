package br.com.meetime.hubspot.domain.exception;

import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HubspotException extends RuntimeException{
    private final int status;
    private final ApiErroResponse apiErro;
}
