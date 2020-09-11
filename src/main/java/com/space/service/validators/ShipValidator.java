package com.space.service.validators;

import com.space.model.Ship;

import java.sql.Date;

public class ShipValidator {

    public static boolean isValid(Ship ship) {

        if (ship.getName() == null || ship.getName().isEmpty()) {
            return false;
        }
        if (ship.getPlanet() == null  || ship.getPlanet().isEmpty()) {
            return false;
        }
        if (ship.getProdDate() == null) {
            return false;
        }
        if (ship.getSpeed() == null) {
            return false;
        }
        if (ship.getCrewSize() == null) {
            return false;
        }

        if (ship.getName().length() > 50) {
            return false;
        }
        if (ship.getPlanet().length() > 50) {
            return false;
        }
        if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
            return false;
        }
        if (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99) {
            return false;
        }
        if (ship.getProdDate() < 0) {
            return false;
        }
        Date prodDate = new Date(ship.getProdDate());
        if ((prodDate.getYear() + 1900) < 2800 || (prodDate.getYear() + 1900) > 3019) {
            return false;
        }

        return true;
    }

}