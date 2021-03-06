/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.repository;

import com.alkemy.desafioDisney.entities.MovieEntity;
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
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, 
        JpaSpecificationExecutor<MovieEntity>  {
    
    @Override
    List<MovieEntity> findAll(Specification<MovieEntity> specification);
}
