/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.errors;

import org.springframework.validation.BindingResult;

/**
 *
 * @author Jose Ignacio
 */
public class BadRequestException extends RuntimeException{
    
    private static final String DESCRIPTION = "BAD REQUEST EXCEPTION";

    private final transient BindingResult result;

    public BadRequestException(String detail, BindingResult result) {
        super(DESCRIPTION + ". " + detail);
        this.result = result;
    }

    public BadRequestException(BindingResult result) {
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }
    
}
