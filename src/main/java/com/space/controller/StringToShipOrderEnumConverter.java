package com.space.controller;

import org.springframework.core.convert.converter.Converter;

public class StringToShipOrderEnumConverter implements Converter<String, ShipOrder> {

    @Override
    public ShipOrder convert(String from) {
        return ShipOrder.valueOf(from);
    }

}
