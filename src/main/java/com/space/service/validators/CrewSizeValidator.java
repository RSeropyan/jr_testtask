package com.space.service.validators;

public class CrewSizeValidator {

    public static boolean isValid(Integer crewSize) {

        return !(crewSize == null || crewSize < 1 || crewSize > 9999);

    }

}
