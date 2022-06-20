/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.service.impl;

import com.alkemy.desafioDisney.dto.GenderDTO;
import com.alkemy.desafioDisney.entities.GenderEntity;
import com.alkemy.desafioDisney.mapper.GenderMapper;
import com.alkemy.desafioDisney.repository.GenderRepository;
import com.alkemy.desafioDisney.service.GenderService;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Ignacio
 */

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderMapper genderMapper;
    
    @Transactional
    @Override
    public GenderDTO save(GenderDTO dto) {
        
         GenderEntity entity = genderMapper.genderDTO2Entity(dto);
        
        GenderEntity entitySaved = genderRepository.save(entity);
        
        GenderDTO resultDTO = 
                genderMapper.genderEntity2DTO(entitySaved);
        
        
        return resultDTO;
        
        
    }
    
    @Override
    public void delete(Long id) {
        Optional <GenderEntity> entity = this.genderRepository.findById(id);
      
        if (!entity.isPresent()) {
            //Hacer excepcion
        }
        
        this.genderRepository.delete(entity.get());
    }

    
    
}
