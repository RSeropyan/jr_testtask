package com.space.service;

import com.space.model.Ship;
import com.space.service.exceptions.ShipNotFoundException;
import com.space.model.utils.ShipRatingCalculator;
import com.space.repository.ShipRepository;
import com.space.service.validators.IdValidator;
import com.space.service.validators.ShipValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Ship getShipById(Long id) {

        boolean isValid = IdValidator.isValid(id);
        if (isValid) {
            Optional<Ship> ship = shipRepository.findById(id);
            if (ship.isPresent()) {
                return ship.get();
            }
            else {
                throw new ShipNotFoundException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }

    }

    public void deleteShipById(Long id) {

        boolean isValid = IdValidator.isValid(id);
        if (isValid) {
            if (shipRepository.existsById(id)) {
                shipRepository.deleteById(id);
            }
            else {
                throw new ShipNotFoundException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }

    }

    public Ship createShip(Ship ship) {

        if (ShipValidator.isValid(ship)) {
            Ship newShip = new Ship();

            String name = ship.getName();
            newShip.setName(name);

            String planet = ship.getPlanet();
            newShip.setPlanet(planet);

            String shipType = ship.getShipType();
            newShip.setShipType(shipType);

            Long prodDate = ship.getProdDate();
            newShip.setProdDate(prodDate);

            Boolean isUsed;
            if(ship.getIsUsed() != null) {
                isUsed = ship.getIsUsed();
            }
            else {
                isUsed = false;
            }
            newShip.setIsUsed(isUsed);

            Double speed = ship.getSpeed();
            newShip.setSpeed(speed);

            Integer crewSize = ship.getCrewSize();
            newShip.setCrewSize(crewSize);

            Double rating = ShipRatingCalculator.calculate(isUsed, speed, prodDate);
            newShip.setRating(rating);

            newShip = shipRepository.save(newShip);
            return newShip;
        }
        else {
            throw new IllegalArgumentException();
        }

    }

}
