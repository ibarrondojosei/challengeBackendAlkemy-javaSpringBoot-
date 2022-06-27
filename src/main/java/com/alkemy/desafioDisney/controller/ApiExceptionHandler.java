/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.controller;

import com.alkemy.desafioDisney.dto.ApiErrorsDTO;
import com.alkemy.desafioDisney.errors.BadGatewayException;
import com.alkemy.desafioDisney.errors.BadRequestException;
import com.alkemy.desafioDisney.errors.ConflictException;
import com.alkemy.desafioDisney.errors.ForbiddenException;
import com.alkemy.desafioDisney.errors.NotFoundException;
import com.alkemy.desafioDisney.errors.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Jose Ignacio
 */

@ControllerAdvice
public class ApiExceptionHandler {
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class,        
        org.springframework.security.access.AccessDeniedException.class})
    @ResponseBody
    public void unauthorizedRequest() {
       
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> notFoundRequest(
            NotFoundException exception) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.server.ServerWebInputException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> badRequest(Exception exception) {

        return buildResponseEntity(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<ApiErrorsDTO> handleException(
            BadRequestException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errors = exc.getResult().getFieldErrors().stream().map(
                FieldError::getDefaultMessage).collect(Collectors.toList());
        return buildResponseEntity(httpStatus, 
                new RuntimeException("Data enviada es invalida"), errors);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> conflict(ConflictException exception) {
        return buildResponseEntity(HttpStatus.CONFLICT, exception);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> forbidden(ForbiddenException exception) {
        return buildResponseEntity(HttpStatus.FORBIDDEN, exception);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({BadGatewayException.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> badGateway(BadGatewayException exception) {
        return buildResponseEntity(HttpStatus.BAD_GATEWAY, exception);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public ResponseEntity<ApiErrorsDTO> Throwable(Throwable exception) { 
        exception.printStackTrace();
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<ApiErrorsDTO> buildResponseEntity(
            HttpStatus httpStatus, Throwable exc) {
        return buildResponseEntity(httpStatus, exc, null);
    }

    private ResponseEntity<ApiErrorsDTO> buildResponseEntity(
            HttpStatus httpStatus, Throwable exc, List<String> errors) {
        ApiErrorsDTO error = new ApiErrorsDTO();
        error.setMessage("USRMSG-" + exc.getMessage());
        error.setStatus(httpStatus);
        error.setErrors(errors);
        return new ResponseEntity<>(error, httpStatus);
    }
    
}
