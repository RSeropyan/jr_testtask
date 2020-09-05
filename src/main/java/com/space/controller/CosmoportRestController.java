package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class CosmoportRestController {

    private final ShipService shipService;

    public CosmoportRestController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("ships")
    public ResponseEntity getAllShips() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        List<Ship> ships = shipService.getAllShips();
        return new ResponseEntity<>(ships, headers, HttpStatus.OK);
    }

}
