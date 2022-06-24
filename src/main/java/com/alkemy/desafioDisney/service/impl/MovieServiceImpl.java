/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.service.impl;

import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.dto.filters.MovieFiltersDTO;
import com.alkemy.desafioDisney.entities.MovieEntity;
import com.alkemy.desafioDisney.mapper.MovieMapper;
import com.alkemy.desafioDisney.repository.MovieRepository;
import com.alkemy.desafioDisney.repository.specification.MovieSpecification;
import com.alkemy.desafioDisney.service.MovieService;
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
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    
    @Autowired
    private MovieSpecification movieSpecification;
    
    @Transactional
    @Override
    public MovieDTO save(MovieDTO dto) {
//        dto.getListCharacter().forEach(x -> {
//            System.out.println("DTo ID LISTA CHARACTER: " + x.getId());
//        });
        
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
            //Hacer excepcion
        }
        
        this.movieMapper.entityRefreshValues(entity.get(), basicDTO);
        
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
            //Hacer excepcion
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

    
    
    
     
    
}
