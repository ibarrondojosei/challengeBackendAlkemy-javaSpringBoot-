/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alkemy.desafioDisney.service;

import com.alkemy.desafioDisney.dto.CharacterBasicDTO;
import com.alkemy.desafioDisney.dto.CharacterDTO;

/**
 *
 * @author Jose Ignacio
 */
public interface CharacterService {
    
    CharacterDTO save(CharacterDTO dto);
    
    CharacterDTO update(Long id, CharacterBasicDTO basicDTO);
    
    void delete(Long id);
}
