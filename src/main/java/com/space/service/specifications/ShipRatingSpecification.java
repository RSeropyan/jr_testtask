package com.space.service.specifications;

import com.space.model.Ship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ShipRatingSpecification {

    public static Specification<Ship> isGreaterThanOrEqualToMinRating(Double minRating) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minRating == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
                }
            }
        };
    }

    public static Specification<Ship> isLessThanOrEqualToMaxRating(Double maxRating) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (maxRating == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating);
                }
            }
        };
    }

}
