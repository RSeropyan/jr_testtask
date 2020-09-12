package com.space.service.validators;

public class NameValidator {

    public static boolean isValid(String name) {

        return !(name == null || name.isEmpty() || name.length() > 50);

    }

}
