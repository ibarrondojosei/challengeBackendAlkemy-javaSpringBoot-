/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.mapper;

import com.alkemy.desafioDisney.dto.GenderDTO;
import com.alkemy.desafioDisney.entities.GenderEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose Ignacio
 */

@Component
public class GenderMapper {
    
    public GenderEntity genderDTO2Entity(GenderDTO dto){
        
        GenderEntity entity = new GenderEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
               
        
        return entity;
    }
    
    public GenderDTO genderEntity2DTO(GenderEntity entity){
        
        GenderDTO dto = new GenderDTO();
        
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
                
        
        return dto;
    }
    
    
    
    
    
}
