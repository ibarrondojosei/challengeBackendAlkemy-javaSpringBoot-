/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.controller;

import com.alkemy.desafioDisney.dto.MovieBasicDTO;
import com.alkemy.desafioDisney.dto.MovieDTO;
import com.alkemy.desafioDisney.errors.BadRequestException;
import com.alkemy.desafioDisney.service.MovieService;
import com.alkemy.desafioDisney.service.impl.MovieServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Ignacio
 */

@RestController
@RequestMapping("movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    @PostMapping
    public ResponseEntity <MovieDTO> save (@RequestBody MovieDTO movie){
        
        MovieDTO movieSave = movieService.save(movie);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSave);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity <MovieDTO> delete (@PathVariable Long id){
        
        this.movieService.delete(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@RequestBody MovieBasicDTO dto,
                                BindingResult result, @PathVariable Long id){
        
        if(result.hasErrors()){
            throw new BadRequestException(result);
        }
        MovieDTO movieDTO = this.movieService.update(id, dto);
            
        return ResponseEntity.ok().body(movieDTO);
    }
    
    
    @GetMapping
    public ResponseEntity <List<MovieBasicDTO>> getByCombinedFilters (
            @RequestParam (required = false) String title,
            @RequestParam (required = false) Long gender,
            @RequestParam (required = false, defaultValue = "ASC") String order
    ){
        
        List<MovieBasicDTO> movieBasicDTOList = this.movieService.getByFilters(
                title, gender, order);
        
        return ResponseEntity.ok().body(movieBasicDTOList);
            
    }
    
    
    @PostMapping("/{idMovie}/Characters/{idCharacter}")
    public ResponseEntity <MovieDTO> addCharacter (@PathVariable Long idMovie, 
                                  @PathVariable Long idCharacter){
        
        MovieDTO movieDTO = this.movieService.addCharacter (idMovie, idCharacter);
        
        return ResponseEntity.ok().body(movieDTO);
        
    }
    
    @DeleteMapping("/{idMovie}/Characters/{idCharacter}")
    public ResponseEntity <MovieDTO> removeCharacter (@PathVariable Long idMovie, 
                                  @PathVariable Long idCharacter){
        
        MovieDTO movieDTO = 
                this.movieService.removeCharacter (idMovie, idCharacter);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
    }
    
    
    
}
