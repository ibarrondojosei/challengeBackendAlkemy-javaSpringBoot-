/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.errors;

/**
 *
 * @author Jose Ignacio
 */
public class UnauthorizedException extends RuntimeException{
    
     private static final String DESCRIPTION = "UNAUTHORIZED EXCEPTION - 401";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
