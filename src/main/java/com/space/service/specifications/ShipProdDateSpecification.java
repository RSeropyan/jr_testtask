package com.space.service.specifications;

import com.space.model.Ship;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Date;

public class ShipProdDateSpecification {

    public static Specification<Ship> isGreaterThanOrEqualToMinDate(Long after) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (after == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Date(after));
                }
            }
        };
    }

    public static Specification<Ship> isLessThanOrEqualToMaxDate(Long before) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (before == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Date(before));
                }
            }
        };
    }

}
