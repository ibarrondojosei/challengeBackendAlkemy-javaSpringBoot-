/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.service.impl;

import com.alkemy.desafioDisney.dto.CharacterBasicDTO;
import com.alkemy.desafioDisney.dto.CharacterDTO;
import com.alkemy.desafioDisney.dto.filters.CharacterFiltersDTO;
import com.alkemy.desafioDisney.entities.CharacterEntity;
import com.alkemy.desafioDisney.errors.NotFoundException;
import com.alkemy.desafioDisney.mapper.CharacterMapper;
import com.alkemy.desafioDisney.repository.CharacterRepository;
import com.alkemy.desafioDisney.repository.specification.CharacterSpecification;
import com.alkemy.desafioDisney.service.CharacterService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Ignacio
 */

@Service
public class CharacterServiceImpl implements CharacterService {
    
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterSpecification characterSpecification; 
    
    @Transactional
    @Override
    public CharacterDTO save (CharacterDTO dto){
        
        CharacterEntity entity = characterMapper.charaterDTO2Entity(dto);
        
        CharacterEntity entitySaved = characterRepository.save(entity);
        
        CharacterDTO resultDTO = 
                characterMapper.charaterEntity2DTO(entitySaved,false);
        
        
        return resultDTO;
    }
    
    @Transactional
    @Override
    public CharacterDTO update(Long id, CharacterBasicDTO basicDTO) {
        Optional <CharacterEntity> entity = 
                this.characterRepository.findById(id);
        
        if (!entity.isPresent()) {
            throw new NotFoundException("El ID: "+ id 
                    +" no pertenece a un personaje");
        }
        
        this.characterMapper.entityRefreshValues(entity.get(), basicDTO);
        
        CharacterEntity characterEntity = 
                this.characterRepository.save(entity.get());
        CharacterDTO result = 
                this.characterMapper.charaterEntity2DTO(characterEntity,true);
        
        return result;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional <CharacterEntity> entity = 
                this.characterRepository.findById(id);
        
        if (!entity.isPresent()) {
            //Colocar la excepcion
        }
        this.characterRepository.delete(entity.get());
    }

    @Transactional
    @Override
    public List<CharacterBasicDTO> getByFilters(
                                    String name, Integer age, Double weight,
                                        List<Long> movieList, String order) {
        
         CharacterFiltersDTO filterDTO = new CharacterFiltersDTO(
                 name, age, weight, movieList, order);
        
        List<CharacterEntity> characterList= 
                this.characterRepository.findAll(
                        this.characterSpecification.getByFilters(filterDTO));
        
        List<CharacterBasicDTO> characterBasicDTOList = 
                this.characterMapper.characterEntityList2BasicDTOList(
                        characterList);
               
        return characterBasicDTOList;
        
    }

    
}
