package com.space.service.validators;

public class SpeedValidator {

    public static boolean isValid(Double speed) {

        return !(speed == null || speed < 0.01 || speed > 0.99);

    }

}
