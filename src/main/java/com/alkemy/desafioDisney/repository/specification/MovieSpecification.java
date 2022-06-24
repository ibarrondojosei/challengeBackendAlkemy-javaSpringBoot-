/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alkemy.desafioDisney.repository.specification;

import com.alkemy.desafioDisney.dto.filters.MovieFiltersDTO;
import com.alkemy.desafioDisney.entities.GenderEntity;
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
public class MovieSpecification {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getTitle())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), //nombre del atributo
                                "%" + filterDTO.getTitle().toLowerCase() + "%"
                        ));

            }
            if (filterDTO.getGender() != null) {
                Join<GenderEntity, MovieEntity> join = root.join("gender", JoinType.INNER);//nombre del atributo
                Expression<String> genderId = join.get("id"); //nombre de la columna
                predicates.add(genderId.in(filterDTO.getGender()));
            }

            //remueve duplicados
            query.distinct(true);

            // Resuelve el orden
            String orderByField = "date"; //"date = nombre del atributo
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
