package com.space.service.specifications;

import com.space.model.Ship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShipPlanetSpecification {

    public static Specification<Ship> isEqualToPlanet(String planet) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (planet == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
                }
            }
        };
    }

}
