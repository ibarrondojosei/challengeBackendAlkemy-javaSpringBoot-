/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.repository.specification;

import com.alkemy.desafioDisney.dto.filters.CharacterFiltersDTO;
import com.alkemy.desafioDisney.entities.CharacterEntity;
import com.alkemy.desafioDisney.entities.MovieEntity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author Jose Ignacio
 */
@Component
public class CharacterSpecification {
    
    public Specification<CharacterEntity> getByFilters(
                                        CharacterFiltersDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), //nombre del atributo
                        "%" + filterDTO.getName().toLowerCase() + "%"
                        ));

            }
            if (filterDTO.getAge() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("age"), filterDTO.getAge()));
            }
            
            if (filterDTO.getWeight() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("weight"), filterDTO.getWeight()));
            }
            
            if (filterDTO.getMovieList()!= null) {
                Join<MovieEntity, CharacterEntity> join = 
                        root.join("listMovie", JoinType.INNER);//nombre del atributo de la Entidad character
                Expression<String> movieId = join.get("id"); //nombre del atributo de la Entidad movie
                predicates.add(movieId.in(filterDTO.getMovieList()));
            }

            //remueve duplicados
            query.distinct(true);

            // Resuelve el orden
            String orderByField = "name"; //"name = nombre del atributo
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and((javax.persistence.criteria.Predicate[])
                    predicates.toArray(new Predicate[0]));
        };

    }
    
}
