/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto;


import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter

public class CharacterDTO {
    
    private Long id;
    
    private String image;
    
    private String name;
    
    private Integer age;
    
    private Double weight;
    
    private String history;
        
    private List<MovieDTO> listMovie;
    
    
    
}
