package com.space.service.validators;

import java.sql.Date;

public class ProdDateValidator {

    public static boolean isValid(Long prodDate) {
        if (prodDate == null) {
            return false;
        }
        else {
            Date date = new Date(prodDate);
            return !(prodDate < 0 || (date.getYear() + 1900) < 2800 || (date.getYear() + 1900) > 3019);
        }
    }

}
