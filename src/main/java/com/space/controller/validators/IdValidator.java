package com.space.controller.validators;

public class IdValidator{

    public static boolean isValid(Long id) {
        if (id != null) {
            return id > 0;
        }
        return false;
    }

}
