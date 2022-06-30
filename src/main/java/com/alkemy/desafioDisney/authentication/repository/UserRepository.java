/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafiodisney.authentication.repository;

import com.alkemy.desafiodisney.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Ignacio
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    UserEntity findByUsername (String userName);
}
