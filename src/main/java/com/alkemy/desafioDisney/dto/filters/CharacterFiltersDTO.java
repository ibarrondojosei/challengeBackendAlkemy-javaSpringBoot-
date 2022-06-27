/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.dto.filters;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose Ignacio
 */

@Getter
@Setter
public class CharacterFiltersDTO {
    
    private String name;
    private Integer age;
    private Double weight;
    private List<Long> movieList;
    private String order;

    public CharacterFiltersDTO(String name, Integer age,Double weight,
            List<Long> movieList, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movieList = movieList;
        this.order = order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC") == 0;}
    
}
