/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alkemy.desafioDisney.service;

import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import java.util.List;

/**
 *
 * @author Jose Ignacio
 */
public interface MovieService {
    
    MovieDTO save (MovieDTO dto);
    
    MovieDTO update (Long id, MovieBasicDTO basicDTO);
    
    void delete (Long id);
    
    List<MovieBasicDTO> getByFilters (String title, Long gender, String order);
    
    MovieDTO addCharacter (Long idMovie, Long idCharacter);
    
    MovieDTO removeCharacter (Long idMovie, Long idCharacter);
    
}
