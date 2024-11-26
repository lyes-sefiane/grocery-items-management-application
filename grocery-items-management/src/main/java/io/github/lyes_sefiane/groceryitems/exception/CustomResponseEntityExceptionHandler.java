package io.github.lyes_sefiane.groceryitems.exception;


import io.github.lyes_sefiane.groceryitems.exception.entities.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.exception
 * Class : CustomResponseEntityExceptionHandler.java
 * User : Lyes Sefiane
 * Created : 2023-09-27 1:34 PM
 */
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex      : RuntimeException
     * @param request : WebRequest
     * @return ResponseEntity<Object>
     * @see ResponseEntity<Object>
     */
    @ExceptionHandler(GroceryItemNotFoundException.class)
    public final ResponseEntity<Object> handleGroceryItemNotFound(RuntimeException ex, WebRequest request) {

        return buildResponseEntity(//
                new ExceptionResponse(//
                        ex.getMessage(), //
                        HttpStatus.NOT_FOUND, //
                        ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    /**
     * Handle Constraint Violation
     * <p>
     * Thrown when path variable validation fail.
     *
     * @param ex      : ConstraintViolationException
     * @param request : WebRequest
     * @return ResponseEntity<Object>
     * @see ResponseEntity<Object>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(//
                "validation error", //
                HttpStatus.BAD_REQUEST, //
                ((ServletWebRequest) request).getRequest().getRequestURI());

        exceptionResponse.addErrors(ex.getConstraintViolations());

        return buildResponseEntity(exceptionResponse);
    }


    /**
     * Handle Method Argument Not Valid
     * <p>
     * <p>
     * Thrown when bean validation fail.
     *
     * @param ex      : MethodArgumentNotValidException
     * @param headers : HttpHeaders
     * @param status  : HttpStatusCode
     * @param request : WebRequest
     * @return ResponseEntity<Object>
     * @see ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(//
                "validation error", //
                HttpStatus.BAD_REQUEST, //
                ((ServletWebRequest) request).getRequest().getRequestURI());

        exceptionResponse.addFieldErrors(ex.getBindingResult().getFieldErrors());
        exceptionResponse.addGlobalErrors(ex.getBindingResult().getGlobalErrors());

        return buildResponseEntity(exceptionResponse);
    }

    /**
     * Build Response Entity
     *
     * @param exceptionResponse : ExceptionResponse
     * @return ResponseEntity<Object>
     * @see ExceptionResponse
     */
    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getHttpStatus());
    }
}
