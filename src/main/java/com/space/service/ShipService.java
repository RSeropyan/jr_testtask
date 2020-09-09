package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipNotFoundException;
import com.space.repository.ShipRepository;
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
        // id is 100% not null because it has been previously checked in Controller with IdValidator
        Optional<Ship> ship = shipRepository.findById(id);
        if (ship.isPresent()) {
            return ship.get();
        }
        else {
            throw new ShipNotFoundException();
        }
    }

    public void deleteShipById(Long id) {
        // id is 100% not null because it has been previously checked in Controller with IdValidator
        if(shipRepository.existsById(id)) {
            shipRepository.deleteById(id);
        }
        else {
            throw new ShipNotFoundException();
        }
    }
}
