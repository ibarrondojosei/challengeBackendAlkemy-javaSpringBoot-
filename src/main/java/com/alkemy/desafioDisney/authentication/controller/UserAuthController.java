/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafiodisney.authentication.controller;


import com.alkemy.desafioDisney.errors.BadRequestException;
import com.alkemy.desafioDisney.errors.ForbiddenException;
import com.alkemy.desafiodisney.authentication.dto.AuthRequest;
import com.alkemy.desafiodisney.authentication.dto.AuthResponse;
import com.alkemy.desafiodisney.authentication.dto.UserDTO;
import com.alkemy.desafiodisney.authentication.service.JwtUtils;
import com.alkemy.desafiodisney.authentication.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
/**
 *
 * @author Jose Ignacio
 */

@RestController
@RequestMapping("/auth")
public class UserAuthController {
  
    private UserDetailsCustomService userDetailsCustomService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public UserAuthController(UserDetailsCustomService userDetailsCustomService,
                              AuthenticationManager authenticationManager,
                              JwtUtils jwtTokenUtil) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/singUp")
    public ResponseEntity<AuthResponse> singUp(@Valid @RequestBody UserDTO user, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        this.userDetailsCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/singIn")
    public ResponseEntity<AuthResponse> singIn(@RequestBody AuthRequest authRequest){

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new ForbiddenException("Usuario o Password incorrecto");
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    
    
}
