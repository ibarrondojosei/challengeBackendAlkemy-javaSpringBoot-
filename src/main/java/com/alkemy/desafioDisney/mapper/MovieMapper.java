/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.mapper;

import com.alkemy.desafioDisney.dto.CharacterDTO;
import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.entities.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 *
 * @author Jose Ignacio
 */

@Component
public class MovieMapper {
    
    @Autowired
    private CharacterMapper characterMapper;
    
     public MovieEntity movieDTO2Entity(MovieDTO dto){
        
        MovieEntity entity = new MovieEntity();
        
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setQualification(dto.getQualification());
        entity.setId(dto.getId());
        entity.setGender(dto.getGender());
        
        
        return entity;
    }
    
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacter){
        
        MovieDTO dto = new MovieDTO();
        
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setDate(entity.getDate());
        dto.setQualification(entity.getQualification());
        dto.setGender(entity.getGender());
        
         if (loadCharacter==true) {
            
            List<CharacterDTO> listCharacterDTO = 
                    this.characterMapper.characterEntity2DTOList(entity.getListCharacter(), false);
            
            dto.setListCharacter(listCharacterDTO);
        }
        
        return dto;
    }
    
    public List <MovieDTO> movieEntity2DTOList (List<MovieEntity> entityList, boolean loadCharacter){
        
        List<MovieDTO> dtoList = new ArrayList();
        
        for (MovieEntity entity : entityList) {
            
            dtoList.add(this.movieEntity2DTO(entity, loadCharacter));
            
        }
        
        return dtoList;
    }
    
    public void entityRefreshValues (MovieEntity entity, MovieBasicDTO basicDTO){
        
        entity.setId(basicDTO.getId());
        entity.setImage(basicDTO.getImage());
        entity.setTitle(basicDTO.getTitle());
        entity.setDate(basicDTO.getDate());
        entity.setQualification(basicDTO.getQualification());
                    
    }

   
}
