/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.service.impl;

import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.dto.filters.MovieFiltersDTO;
import com.alkemy.desafioDisney.entities.CharacterEntity;
import com.alkemy.desafioDisney.entities.MovieEntity;
import com.alkemy.desafioDisney.errors.NotFoundException;
import com.alkemy.desafioDisney.mapper.MovieMapper;
import com.alkemy.desafioDisney.repository.CharacterRepository;
import com.alkemy.desafioDisney.repository.MovieRepository;
import com.alkemy.desafioDisney.repository.specification.MovieSpecification;
import com.alkemy.desafioDisney.service.MovieService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Ignacio
 */

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @Autowired
    private MovieMapper movieMapper;
    
    @Autowired
    private MovieSpecification movieSpecification;
    
    @Transactional
    @Override
    public MovieDTO save(MovieDTO dto) {

        
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        
        
        
        MovieEntity entitySaved = movieRepository.save(entity);
        
        MovieDTO resultDTO = 
                movieMapper.movieEntity2DTO(entitySaved,true);
        
        
        return resultDTO;
    }

    @Transactional
    @Override
    public MovieDTO update(Long id, MovieBasicDTO basicDTO) {
         Optional <MovieEntity> entity = 
                this.movieRepository.findById(id);
        
        if (!entity.isPresent()) {
            throw new NotFoundException("El ID: "+ id 
                    +" no pertenece a una pelicula");
        }
        
       this.movieMapper.entityRefreshValues(entity.get(), basicDTO);
      
        entity.get().setImage(basicDTO.getImage());
        entity.get().setTitle(basicDTO.getTitle());
        entity.get().setQualification(basicDTO.getQualification());
       
        MovieEntity characterEntity = 
                this.movieRepository.save(entity.get());
        MovieDTO result = 
                this.movieMapper.movieEntity2DTO(characterEntity,true);
        
        return result;
    }
    
    @Transactional
    @Override
    public void delete(Long id) {
        
      Optional <MovieEntity> entity = this.movieRepository.findById(id);
      
       
        if (!entity.isPresent()) {
           throw new NotFoundException("El ID: "+ id 
                    +" no pertenece a una pelicula");
        }
        
        this.movieRepository.delete(entity.get());
    }

    @Transactional
    @Override
    public List<MovieBasicDTO> getByFilters(
            String title, Long gender, String order) {
        MovieFiltersDTO filterDTO = new MovieFiltersDTO(title, gender, order);
        
        List<MovieEntity> movieList= 
                this.movieRepository.findAll(
                        this.movieSpecification.getByFilters(filterDTO));
        
        List<MovieBasicDTO> movieBasicDTOList = 
                this.movieMapper.movieEntityList2BasicDTOList(movieList);
               
        return movieBasicDTOList;
    }
    
    @Transactional
    @Override
    public MovieDTO addCharacter(Long idMovie, Long idCharacter) {
        
        MovieEntity movieEntity = 
                this.movieRepository.findById(idMovie).orElse(null);
        CharacterEntity characterEntity = 
                this.characterRepository.findById(idCharacter).orElse(null);
        
        if (characterEntity == null || movieEntity == null || 
                movieEntity.getListCharacter().contains(characterEntity)) {
            
            throw new NotFoundException ("No se pudo agregar el personaje");
        }
        
        movieEntity.getListCharacter().add(characterEntity);
        
        MovieEntity entitySave = this.movieRepository.save(movieEntity);
        
        MovieDTO dtoResult = this.movieMapper.movieEntity2DTO(entitySave, true);
        
        return dtoResult;
        
    }

    @Transactional
    @Override
    public MovieDTO removeCharacter(Long idMovie, Long idCharacter) {
         MovieEntity movieEntity = 
                this.movieRepository.findById(idMovie).orElse(null);
        CharacterEntity characterEntity = 
                this.characterRepository.findById(idCharacter).orElse(null);
        
        if (characterEntity == null || movieEntity == null) {
            
            throw new NotFoundException ("No se pudo remover el personaje");
        }
        
        movieEntity.getListCharacter().remove(characterEntity);
        
        MovieEntity entitySave = this.movieRepository.save(movieEntity);
        
        MovieDTO dtoResult = this.movieMapper.movieEntity2DTO(entitySave, true);
        
        return dtoResult;
        
    }

    
    
    
     
    
}
