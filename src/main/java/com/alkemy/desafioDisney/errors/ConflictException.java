/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.errors;

/**
 *
 * @author Jose Ignacio
 */
public class ConflictException extends RuntimeException {
    
    private static final String DESCRIPTION = "CONFLICT EXCEPTION";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
