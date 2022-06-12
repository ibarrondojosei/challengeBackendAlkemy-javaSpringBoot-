/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Jose Ignacio
 * 
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="personaje")

public class CharacterEntity {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")  
    private String id;
    
    private String image;
    
    private String name;
    
    private Integer age;
    
    private Double weight;
    
    private String historia;
    
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="personaje_pelicula", 
            joinColumns = @JoinColumn(name="personaje_id"),
            inverseJoinColumns = @JoinColumn(name="pelicula_id"))
    
    private List<MovieEntity> listCharacter= new ArrayList<>();
    
    
    
    
    
}
