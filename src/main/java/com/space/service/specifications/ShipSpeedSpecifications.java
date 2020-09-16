package com.space.service.specifications;

import com.space.model.Ship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShipSpeedSpecifications {

    public static Specification<Ship> isGreaterThanOrEqualToMinSpeed(Double minSpeed) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minSpeed == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), minSpeed);
                }
            }
        };
    }

    public static Specification<Ship> isLessThanOrEqualToMaxSpeed(Double maxSpeed) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (maxSpeed == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), maxSpeed);
                }
            }
        };
    }

}
