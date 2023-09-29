package com.lyess.groceryitems.exception.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.exception.entities
 * Class : ExceptionResponse.java
 * User : Lyes Sefiane
 * Created : 2023-09-29 11:30 AM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    @JsonIgnore
    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;

    private String status;

    private String error;

    private String message;

    private String path;

    private List<ErrorResponse> details;


    public ExceptionResponse(String message, HttpStatus httpStatus, String path) {
        this.timeStamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.status = httpStatus.value() + "";
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    private ValidationError buildValidationError(String object, String field, Object rejectedValue, String message) {
        return new ValidationError.Builder()//
                .object(object)//
                .field(field)//
                .rejectedValue(rejectedValue)//
                .message(message)//
                .build();
    }

    private ValidationError buildValidationError(String object, String message) {
        return new ValidationError.Builder()//
                .object(object)//
                .message(message)//
                .build();
    }

    private void addError(ErrorResponse error) {
        if (details == null) {
            details = new ArrayList<>();
        }
        details.add(error);
    }

    public void addErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.stream()//
                .map(constraintViolation -> buildValidationError(constraintViolation.getRootBeanClass().getSimpleName(), //
                        ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName(), //
                        constraintViolation.getInvalidValue(), //
                        constraintViolation.getMessage()))//
                .forEach(this::addError);
    }

    public void addFieldErrors(List<FieldError> fieldErrors) {
        fieldErrors.stream()//
                .map(fieldError -> buildValidationError(fieldError.getObjectName(), //
                        fieldError.getField(), //
                        fieldError.getRejectedValue(), //
                        fieldError.getDefaultMessage()))//
                .forEach(this::addError);
    }

    public void addGlobalErrors(List<ObjectError> globalErrors) {
        globalErrors.stream()//
                .map(globalError -> buildValidationError(globalError.getObjectName(), //
                        globalError.getDefaultMessage()))//
                .forEach(this::addError);
    }

}
