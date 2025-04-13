package br.com.meetime.hubspot.infrastructure.handler;

import br.com.meetime.hubspot.domain.exception.HubspotException;
import br.com.meetime.hubspot.domain.model.ApiErroResponse;
import br.com.meetime.hubspot.infrastructure.mapper.ApiErroResponseMapper;
import br.com.meetime.hubspot.infrastructure.utils.CorrelationIdUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExcecaoGlobalHandler {

    private final CorrelationIdUtils correlationIdUtils;
    private final ApiErroResponseMapper apiErroResponseMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErroResponse erro = setApiErroResponse(HttpStatus.BAD_REQUEST.name(), "Campos inválidos", detalhes);

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErroResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.toList());

        ApiErroResponse erro = setApiErroResponse(HttpStatus.BAD_REQUEST.name(), "Campos inválidos", details);

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(HubspotException.class)
    public ResponseEntity<ApiErroResponse> handleHubspotClientException(HubspotException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getApiErro());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroResponse> handleGenericException(Exception ex) {
        log.error("Erro inesperado", ex);

        ApiErroResponse erro = setApiErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getClass().getSimpleName(), List.of(ex.getMessage()));

        return ResponseEntity.internalServerError().body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErroResponse> handleUnreadableBody(HttpMessageNotReadableException ex) {

        ApiErroResponse erro = setApiErroResponse(HttpStatus.BAD_REQUEST.name(), "Corpo da requisição inválido", List.of(ex.getMostSpecificCause().getMessage()));

        return ResponseEntity.badRequest().body(erro);
    }

    private ApiErroResponse setApiErroResponse(String error, String message, List<String> details) {
        return new ApiErroResponse(error, message, correlationIdUtils.getCorrelationId(), details);
    }
}
