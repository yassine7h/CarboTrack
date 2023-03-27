package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.repositories.DriverRepository;
import com.yassine7h.parcauto.services.interfaces.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getById(int id) {
        return driverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Driver.class));
    }

    @Override
    public int add(Driver driver) {
        Optional<Driver> driverOptional=driverRepository.findById(driver.getId());
        if(driverOptional.isPresent()) throw new ResourceExistException(driver.getId(),Driver.class);
        Driver addedDriver=driverRepository.save(driver);
        return addedDriver.getId();
    }

    @Override
    public void update(Driver driver) {
        Optional<Driver> driverOptional=driverRepository.findById(driver.getId());
        if(!driverOptional.isPresent()) throw new ResourceNotFoundException(driver.getId(),Driver.class);
        driverRepository.save(driver);
    }

    @Override
    public void delete(int id) {
        Optional<Driver> driverOptional=driverRepository.findById(id);
        if(!driverOptional.isPresent()) throw new ResourceNotFoundException(id,Driver.class);
        driverRepository.deleteById(id);
    }
}
