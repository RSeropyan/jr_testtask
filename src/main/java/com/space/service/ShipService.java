package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.utils.ShipRatingCalculator;
import com.space.repository.ShipRepository;
import com.space.service.exceptions.ShipNotFoundException;
import com.space.service.validators.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAllShipsPageable(Integer pageNumber, Integer pageSize, ShipOrder order) {

        String sortingOrder = (order != null ? order.getFieldName() : "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortingOrder));
        return shipRepository.findAll(pageable).getContent();

    }

    public List<Ship> getAllShips() {

        return shipRepository.findAll();

    }

    public Ship getShipById(Long id) {

        boolean isIdValid = IdValidator.isValid(id);
        if (isIdValid) {
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

        boolean isIdValid = IdValidator.isValid(id);
        if (isIdValid) {
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

        if (    NameValidator.isValid(ship.getName()) &&
                PlanetValidator.isValid(ship.getPlanet()) &&
                ProdDateValidator.isValid(ship.getProdDate()) &&
                SpeedValidator.isValid(ship.getSpeed()) &&
                CrewSizeValidator.isValid(ship.getCrewSize())
            ) {
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

    public Ship updateShipById(Long id, Ship modifiedShip) {

        boolean isIdValid = IdValidator.isValid(id);
        if (isIdValid) {
            Optional<Ship> ship = shipRepository.findById(id);
            if (ship.isPresent()) {
                Ship updatedShip = ship.get();

                if(modifiedShip.getName() != null) {
                    String name = modifiedShip.getName();
                    if (NameValidator.isValid(name)) {
                        updatedShip.setName(name);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                if (modifiedShip.getPlanet() != null) {
                    String planet = modifiedShip.getPlanet();
                    if (PlanetValidator.isValid(planet)) {
                        updatedShip.setPlanet(planet);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                if (modifiedShip.getShipType() != null) {
                    String shipType = modifiedShip.getShipType();
                    updatedShip.setShipType(shipType);
                }

                Long prodDate = updatedShip.getProdDate();
                if (modifiedShip.getProdDate() != null) {
                    prodDate = modifiedShip.getProdDate();
                    if (ProdDateValidator.isValid(prodDate)) {
                        updatedShip.setProdDate(prodDate);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                Boolean isUsed = updatedShip.getIsUsed();
                if (modifiedShip.getIsUsed() != null) {
                    isUsed = modifiedShip.getIsUsed();
                    updatedShip.setIsUsed(isUsed);
                }

                Double speed = updatedShip.getSpeed();
                if (modifiedShip.getSpeed() != null) {
                    speed = modifiedShip.getSpeed();
                    if (SpeedValidator.isValid(speed)) {
                        updatedShip.setSpeed(speed);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                if (modifiedShip.getCrewSize() != null) {
                    Integer crewSize = modifiedShip.getCrewSize();
                    if (CrewSizeValidator.isValid(crewSize)) {
                        updatedShip.setCrewSize(crewSize);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }

                Double rating = ShipRatingCalculator.calculate(isUsed, speed, prodDate);
                updatedShip.setRating(rating);

                return updatedShip;
            }
            else {
                throw new ShipNotFoundException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }

    }
}
