/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter
public class MovieBasicDTO {
    
    private Long id;
    
    private String image;
    
    private String title;
    
    private String date;
    
    private Integer qualification;
    
    
}
