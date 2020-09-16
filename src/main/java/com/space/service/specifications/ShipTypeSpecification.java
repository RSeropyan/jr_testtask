package com.space.service.specifications;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ShipTypeSpecification {

    public static Specification<Ship> isEqualToShipType(ShipType shipType) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (shipType == null) {
                    return criteriaBuilder.conjunction();
                }
                else {
                    return criteriaBuilder.equal(root.get("shipType"), shipType);
                }
            }
        };
    }

}
