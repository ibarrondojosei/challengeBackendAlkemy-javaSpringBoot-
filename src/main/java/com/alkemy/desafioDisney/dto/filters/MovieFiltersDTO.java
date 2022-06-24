/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto.filters;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter
public class MovieFiltersDTO {
   
    private String title;
    private Long gender;
    private String order;

    public MovieFiltersDTO(String title, Long gender, String order) {
        this.title = title;
        this.gender = gender;
        this.order = order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}
    
}
