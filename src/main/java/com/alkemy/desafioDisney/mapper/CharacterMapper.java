/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.mapper;

import com.alkemy.desafioDisney.dto.CharacterBasicDTO;
import com.alkemy.desafioDisney.dto.CharacterDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.entities.CharacterEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 *
 * @author Jose Ignacio
 */

@Component
public class CharacterMapper {
    @Autowired
    private MovieMapper movieMapper;
    
    public CharacterEntity charaterDTO2Entity(CharacterDTO dto){
        
        CharacterEntity entity = new CharacterEntity();
        
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        
        
        return entity;
    }
    
    public CharacterDTO charaterEntity2DTO(CharacterEntity entity, boolean loadMovie){
        
        CharacterDTO dto = new CharacterDTO();
        
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        
        if (loadMovie==true) {
            
            List<MovieDTO> listMovieDTO = this.movieMapper.movieEntity2DTOList(entity.getListMovie(), false);
            
            dto.setListMovie(listMovieDTO);
            
        }
        
        return dto;
    }
    
    public List <CharacterDTO> characterEntity2DTOList (List<CharacterEntity> entityList, boolean loadMovie){
        
        List<CharacterDTO> dtoList = new ArrayList();
        
        for (CharacterEntity entity : entityList) {
            
            dtoList.add(this.charaterEntity2DTO(entity, loadMovie));
            
        }
        
        return dtoList;
        
    }
    
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList (List<CharacterEntity> entityList){
        
        List<CharacterBasicDTO> dtoList = new ArrayList();
        
        CharacterBasicDTO dtoBasic;
        
        for (CharacterEntity entity : entityList) {
            
            dtoBasic = new CharacterBasicDTO();
            
            dtoBasic.setId(entity.getId());
            dtoBasic.setName(entity.getName());
            dtoBasic.setImage(entity.getImage());
            dtoBasic.setAge(entity.getAge());
            dtoBasic.setWeight(entity.getWeight());
            dtoBasic.setHistory(entity.getHistory());
            dtoList.add(dtoBasic);
            
        }
        
        return dtoList;
        
    }
    
    public void entityRefreshValues (CharacterEntity characterEntity, CharacterBasicDTO characterBasicDTO){
        
        characterEntity.setId(characterBasicDTO.getId());
        characterEntity.setName(characterBasicDTO.getName());
        characterEntity.setImage(characterBasicDTO.getImage());
        characterEntity.setAge(characterBasicDTO.getAge());
        characterEntity.setWeight(characterBasicDTO.getWeight());
        characterEntity.setHistory(characterBasicDTO.getHistory());
        
        
    }
    
}
