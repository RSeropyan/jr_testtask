package com.space.service.specifications;

import com.space.model.Ship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShipCrewSizeSpecification {

    public static Specification<Ship> isGreaterThanOrEqualToMinCrewSize(Integer minCrewSize) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minCrewSize == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize);
                }
            }
        };
    }

    public static Specification<Ship> isLessThanOrEqualToMaxCrewSize(Integer maxCrewSize) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (maxCrewSize == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize);
                }
            }
        };
    }

}
