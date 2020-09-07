package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipNotFoundException;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class CosmoportRestController {

    private final ShipService shipService;

    public CosmoportRestController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ships")
    public ResponseEntity<List<Ship>> getAllShips(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false, defaultValue = "false") Boolean isUsed,
            @RequestParam(required = false) Double minSpeed,
            @RequestParam(required = false) Double maxSpeed,
            @RequestParam(required = false) Integer minCrewSize,
            @RequestParam(required = false) Integer maxCrewSize,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating,
            @RequestParam(required = false) ShipOrder order,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "3") Integer pageSize
            ) {

        List<Ship> ships = shipService.getAllShips();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Cache-Control", "no-store");

        return new ResponseEntity<>(ships, headers, HttpStatus.OK);
    }

    @GetMapping("/ships/count")
    public ResponseEntity<Integer> getNumberOfShips(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false, defaultValue = "false") Boolean isUsed,
            @RequestParam(required = false) Double minSpeed,
            @RequestParam(required = false) Double maxSpeed,
            @RequestParam(required = false) Integer minCrewSize,
            @RequestParam(required = false) Integer maxCrewSize,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating
    ) {

        List<Ship> ships = shipService.getAllShips();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Cache-Control", "no-store");

        return new ResponseEntity<>(ships.size(), headers, HttpStatus.OK);
    }

    @GetMapping("/ships/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable("id") Long id) {

        HttpHeaders headers = new HttpHeaders();

        try {
            Ship ship = shipService.getShipById(id);
            headers.add("Cache-Control", "no-store");
            headers.add("Content-Type", "application/json; charset=UTF-8");
            return new ResponseEntity<>(ship, headers, HttpStatus.OK);
        } catch (ShipNotFoundException e) {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ships/{id}")
    public ResponseEntity<Ship> deleteShipById(@PathVariable("id") Long id) {

        try {
            shipService.deleteShipById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ShipNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}