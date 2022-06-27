/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.repository;

import com.alkemy.desafioDisney.entities.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Ignacio
 */

@Repository
public interface CharacterRepository extends 
                                    JpaRepository<CharacterEntity, Long>,
                                    JpaSpecificationExecutor<CharacterEntity> {
    
    @Override
    List<CharacterEntity> findAll(Specification<CharacterEntity> specification);
}
