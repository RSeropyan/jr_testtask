package com.space.service.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidator{

    public static boolean isValid(Long id) {
        Pattern pattern = Pattern.compile("^\\+?[1-9]\\d*$");
        Matcher matcher = pattern.matcher(String.valueOf(id));
        return (id != null && matcher.matches());
    }

}
