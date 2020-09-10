package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipNotFoundException;
import com.space.repository.ShipRepository;
import com.space.service.validators.IdValidator;
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
}
