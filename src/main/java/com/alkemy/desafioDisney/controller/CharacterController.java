/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.controller;

import com.alkemy.desafioDisney.dto.CharacterBasicDTO;
import com.alkemy.desafioDisney.dto.CharacterDTO;
import com.alkemy.desafioDisney.errors.BadRequestException;
import com.alkemy.desafioDisney.service.CharacterService;
import com.alkemy.desafioDisney.service.impl.CharacterServiceImpl;
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
@RequestMapping("character")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;
    
    @PostMapping
    public ResponseEntity <CharacterDTO> save (@RequestBody CharacterDTO character){
        
        CharacterDTO characterSave = characterService.save(character);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSave);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity <CharacterDTO> delete (@PathVariable Long id){
        
        this.characterService.delete(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(
            @RequestBody CharacterBasicDTO dto,
            BindingResult result,
            @PathVariable Long id){
        
        if(result.hasErrors()){
            throw new BadRequestException(result);
        }
        CharacterDTO characterDTO = this.characterService.update(id, dto);
            
        return ResponseEntity.ok().body(characterDTO);
    }
    
    @GetMapping
    public ResponseEntity <List<CharacterBasicDTO>> getByCombinedFilters (
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Double weight,
            @RequestParam (required = false) List<Long> movieList,
            @RequestParam (required = false, defaultValue = "DESC") String order
    ){
        
        List<CharacterBasicDTO> characterBasicDTOList = 
                this.characterService.getByFilters(
                        name, age, weight, movieList, order);
        
        return ResponseEntity.ok().body(characterBasicDTOList);
            
    }
}
