/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
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

public class PeliculaEntity {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")  
    private String id;
    
    private String imagen;
    
    private String titulo;
    
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    @Column(name="fecha_creacion")
    private LocalDate fecha;
    
    private Integer calificacion;
    
    @ManyToMany (mappedBy = "listadoPeliculas", cascade=CascadeType.ALL)
    private List<PersonajeEntity> listaPersonaje = new ArrayList<>();
    
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;
    
    @Column(name="genero_id", nullable = false)
    private String generoId;
    
}
