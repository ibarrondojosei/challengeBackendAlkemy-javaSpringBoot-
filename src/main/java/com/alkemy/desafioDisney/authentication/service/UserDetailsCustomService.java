/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafiodisney.authentication.service;


import com.alkemy.desafiodisney.authentication.config.SecurityConfiguration;
import com.alkemy.desafiodisney.authentication.dto.UserDTO;
import com.alkemy.desafiodisney.authentication.entity.UserEntity;
import com.alkemy.desafiodisney.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 *
 * @author Jose Ignacio
 */
@Service
public class UserDetailsCustomService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//  //  private EmailService emailService;
    @Autowired    
    private SecurityConfiguration securityConfiguration;

   

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) 
                                        throws UsernameNotFoundException {
        
        UserEntity userEntity = userRepository.findByUsername(userName);
        
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario o password no encontrado");
        }
        
        return new User(userEntity.getUsername(), 
                    userEntity.getPassword(), Collections.emptyList());
    }

    @Transactional()
    public boolean save(UserDTO userDto) throws Exception {
        
        String encripted = securityConfiguration.passwordEncoder().encode(
                            userDto.getPassword());
        
        UserEntity userEntity = new UserEntity();
        
        userEntity.setUsername(userDto.getUsername());
        
        userEntity.setPassword(encripted);
        
        UserEntity userResult;
        
        userResult = this.userRepository.save(userEntity);
        
//        emailService.sendEmail("Bienvenida a Disney",
//                          userEntity.getUsername(),"Hola, Bienvenid@ a Disney");
        
        return userResult != null;

    }
    
}
