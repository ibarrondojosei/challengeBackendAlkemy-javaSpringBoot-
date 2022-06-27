/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.errors;

/**
 *
 * @author Jose Ignacio
 */
public class BadGatewayException extends RuntimeException {
    
    private static final String DESCRIPTION = "BAD GATEWAY EXCEPTION";

    public BadGatewayException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
