/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Jose Ignacio Ibarrondo Pelaez
 * 
 * Imagen
■ Título
■ Fecha de creación
■ Calificación (del 1 al 5)
■ Personajes asociados
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="peliculaSerie")
@SQLDelete(sql= "UPDATE peliculaSerie SET deleted=true WHERE id=?")
@Where(clause="deleted=false")

public class MovieEntity {
    
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)   
    private Long id;
    
    private String image;
    
    private String title;
    
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    @Column(name="fecha_creacion")
    private LocalDate date;
    
    @Range (min=1, max=5)
    private Integer qualification;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="pelicula_personaje", 
            joinColumns = @JoinColumn(name="pelicula_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="personaje_id", nullable = false))
    private List<CharacterEntity> listCharacter = new ArrayList<>();
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="genero_id")
    private GenderEntity gender;
    
    private boolean deleted = Boolean.FALSE; 
    
//    
// public void addCharacter(CharacterEntity character){
//        this.listCharacter.add(character);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        MovieEntity movie = (MovieEntity) o;
//
//        if (Double.compare(movie.qualification, qualification) != 0) return false;
//        if (!Objects.equals(id, movie.id)) return false;
//        return Objects.equals(listCharacter, movie.listCharacter);
//    }
//
//
//    public void removePersonaje(MovieEntity movie){
//        this.listCharacter.remove(movie);
//
//    }
    
    
}
