package com.space.model.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

public class ShipRatingCalculator {

    public static Double calculate(Boolean isUsed, Double speed, Long prodDate) {
        double k = isUsed ? 0.5 : 1.0;
        double v = speed;
        int y0 = 3019;
        int y1 = new Date(prodDate).getYear() + 1900;
        double rating = (80 * v * k) / (y0 - y1 + 1);

        BigDecimal bd = new BigDecimal(Double.toString(rating));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

}
