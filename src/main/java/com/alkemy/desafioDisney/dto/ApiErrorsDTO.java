/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorsDTO {
        
    private HttpStatus status;
    
    private String message;
    
    private List<String> errors;
    
    
}
