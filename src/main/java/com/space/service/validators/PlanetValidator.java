package com.space.service.validators;

public class PlanetValidator {

    public static boolean isValid(String planet) {

        return !(planet == null || planet.isEmpty() || planet.length() > 50);

    }

}
