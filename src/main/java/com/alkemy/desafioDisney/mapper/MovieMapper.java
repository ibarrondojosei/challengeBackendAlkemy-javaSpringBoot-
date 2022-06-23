/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.mapper;

import com.alkemy.desafioDisney.dto.CharacterDTO;
import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.entities.CharacterEntity;
import com.alkemy.desafioDisney.entities.MovieEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    @Autowired
    private GenderMapper genderMapper;
    
    //3- Character save 
    public MovieEntity movieDTO2Entity(MovieDTO dto){
        
        MovieEntity entity = new MovieEntity();
        
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setQualification(dto.getQualification());
        entity.setGender(this.genderMapper.genderDTO2Entity(dto.getGender()));       
        entity.setListCharacter(this.characterMapper.characterDTOList2EntityList(dto.getListCharacter()));
        
        
        return entity;
    }
    
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacter){
        
        MovieDTO dto = new MovieDTO();
        
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setDate(entity.getDate());
        dto.setQualification(entity.getQualification());
        dto.setGender(this.genderMapper.genderEntity2DTO(entity.getGender()));
        
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
    
    //2- Character save
    public List<MovieEntity> movieDTO2EntityList(List<MovieDTO> dtoList){
        
        List<MovieEntity> entitylist = new ArrayList();
        
        
        
//        MovieEntity movie = new MovieEntity();
        
        for (MovieDTO dto : dtoList) {
            
            entitylist.add(this.movieDTO2Entity(dto));
            
//        movie.setId(dto.getId());
//        movie.setImage(dto.getImage());
//        movie.setTitle(dto.getTitle());
//        movie.setDate(dto.getDate());
//        movie.setQualification(dto.getQualification());
//        movie.setGender(this.genderMapper.genderDTO2Entity(dto.getGender()));
//            
//            entityList.add(movie);
            
        }
        
        return entitylist;
       
    }
    
    public List<MovieDTO> movieEntitySet2DTOList(Collection<MovieEntity> entities, boolean loadPersonajes) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadPersonajes));
        }
        return dtos;
    }
    
    public void entityRefreshValues (MovieEntity entity, MovieBasicDTO basicDTO){
        
        entity.setId(basicDTO.getId());
        entity.setImage(basicDTO.getImage());
        entity.setTitle(basicDTO.getTitle());
        entity.setDate(LocalDate.parse(basicDTO.getDate()));
        entity.setQualification(basicDTO.getQualification());
                    
    }

   
}
