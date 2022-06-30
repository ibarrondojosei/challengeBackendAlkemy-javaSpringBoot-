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

    @NotEmpty(message = "Ingrese un usuario")
    @Email(message = "Username must be an email")
    private String username;

    @NotEmpty(message = "Ingrese un password")
    @Size(min = 8, max = 16, message = "Min 8 Max 16 caracteres")
    private String password;
}
