/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafiodisney.authentication.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 *
 * @author Jose Ignacio
 */


@Data
public class UserDTO {

    
    @Email(message = "Username must be an email")
    private String username;

   
    @Size(min = 8, max = 16)
    private String password;
}
