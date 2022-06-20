/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.controller;

import com.alkemy.desafioDisney.dto.GenderDTO;
import com.alkemy.desafioDisney.service.GenderService;
import com.alkemy.desafioDisney.service.impl.GenderServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Ignacio
 */

@RestController
@RequestMapping("gender")
public class GenderController {
    
    
    @Autowired
    private GenderService genderService;
    
    @PostMapping
    public ResponseEntity <GenderDTO> save (@Valid @RequestBody GenderDTO gender){
        
        GenderDTO genderSave = genderService.save(gender);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(genderSave);
    }
}
