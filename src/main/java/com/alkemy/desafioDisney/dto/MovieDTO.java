/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto;

import com.alkemy.desafioDisney.entities.CharacterEntity;
import com.alkemy.desafioDisney.entities.GenderEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter
public class MovieDTO {
    
    private Long id;
    
    private String image;
    
    private String title;
    
   
    private LocalDate date;
    
    private Integer qualification;
    
    private List<CharacterDTO> listCharacter;
    
    private GenderDTO gender;
        
   
}
