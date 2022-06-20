/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alkemy.desafioDisney.service;

import com.alkemy.desafioDisney.dto.GenderDTO;

/**
 *
 * @author Jose Ignacio
 */
public interface GenderService {
    
    GenderDTO save (GenderDTO dto);
    
    void delete (Long id);
}
