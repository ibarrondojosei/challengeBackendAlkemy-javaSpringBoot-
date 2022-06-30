/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafiodisney.authentication.config;

import com.alkemy.desafiodisney.authentication.filter.JwtRequestFilter;
import com.alkemy.desafiodisney.authentication.service.UserDetailsCustomService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Jose Ignacio
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
 @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsCustomService).passwordEncoder(
                                this.passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.csrf().disable()
               .authorizeRequests().antMatchers("/auth/*").permitAll()
               .anyRequest().authenticated()
               .and().exceptionHandling()
               .and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //STANTELESS: para cada endpoint hace una atenticacion distinta/nueva

        // agrega un filtro (jwtRequestFilter) , chequea el token del header verificando que los 
        //datos correctos y lo deja pasar antes que el filtro por defecto de 
        //spring (UsernamePasswordAuthenticationFilter)
       httpSecurity.addFilterBefore(
               jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }   
    
}
