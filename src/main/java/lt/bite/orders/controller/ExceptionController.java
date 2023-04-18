package lt.bite.orders.controller;

import jakarta.servlet.http.HttpServletRequest;
import lt.bite.orders.exception.ErrorResponse;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.exception.ValidationErrorResponse;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse customerNotFoundException(HttpServletRequest request, NotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError(cnfe.getLocalizedMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setPath(request.getServletPath());

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse invalidArgumentException(HttpServletRequest request, MethodArgumentNotValidException manve) {
        ValidationErrorResponse validationErrors = new ValidationErrorResponse();

        validationErrors.setPath(request.getServletPath());
        validationErrors.setTimestamp(LocalDateTime.now());
        manve.getFieldErrors().forEach(fieldError -> validationErrors.getError().add(fieldError.getDefaultMessage()));

        return validationErrors;
    }
}
