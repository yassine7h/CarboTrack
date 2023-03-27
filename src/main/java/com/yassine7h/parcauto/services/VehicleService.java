package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.Vehicle;
import com.yassine7h.parcauto.repositories.VehicleRepository;
import com.yassine7h.parcauto.services.interfaces.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getById(int id) {
        return vehicleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Vehicle.class));
    }

    @Override
    public int add(Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(vehicle.getId());
        if(vehicleOptional.isPresent()) throw new ResourceExistException(vehicle.getId(),Vehicle.class);
        Vehicle addedVehicle=vehicleRepository.save(vehicle);
        return addedVehicle.getId();
    }

    @Override
    public void update(Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(vehicle.getId());
        if(!vehicleOptional.isPresent()) throw new ResourceNotFoundException(vehicle.getId(),Vehicle.class);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(int id) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(!vehicleOptional.isPresent()) throw new ResourceNotFoundException(id,Vehicle.class);
        vehicleRepository.deleteById(id);
    }
}
