package br.dev.geovanny.auth_api.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationResourceAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    private ApiError HandleBusinessException(BusinessException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(DevException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    private ApiError HandleDevError(DevException exception) {
        return new ApiError(exception.getMessage());
    }
}
