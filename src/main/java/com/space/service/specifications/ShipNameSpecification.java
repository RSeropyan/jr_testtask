package com.space.service.specifications;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShipNameSpecification {

    public static Specification<Ship> isEqualToName(String name) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (name == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.like(root.get("name"), "%" + name + "%");
                }
            }
        };
    }

}
