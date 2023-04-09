package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.DriverReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.DriverMapper;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.repositories.DriverRepository;
import com.yassine7h.parcauto.services.interfaces.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {

    private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }
    @Override
    public List<DriverResDto> getAllDto() {
        return getAll().stream().map(i->driverMapper.toDriverResDto(i)).collect(Collectors.toList());
    }

    @Override
    public Driver getById(int id) {
        return driverRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Driver.class));
    }

    @Override
    public DriverResDto getByIdDto(int id) {
        return driverMapper.toDriverResDto(getById(id));
    }

    @Override
    public int add(Driver driver) {
        Optional<Driver> driverOptional=driverRepository.findById(driver.getId());
        if(driverOptional.isPresent()) throw new ResourceExistException(driver.getId(),Driver.class);
        Driver addedDriver=driverRepository.save(driver);
        return addedDriver.getId();
    }

    @Override
    public int addDto(DriverReqDto driver) {
        return add(driverMapper.toDriver(driver));
    }

    @Override
    public void update(Driver driver,int id) {
        Optional<Driver> driverOptional=driverRepository.findById(id);
        if(!driverOptional.isPresent()) throw new ResourceNotFoundException(driver.getId(),Driver.class);
        driver.setId(id);
        driverRepository.save(driver);
    }

    @Override
    public void updateDto(DriverReqDto driver,int id) {
        update(driverMapper.toDriver(driver),id);
    }

    @Override
    public void delete(int id) {
        Optional<Driver> driverOptional=driverRepository.findById(id);
        if(!driverOptional.isPresent()) throw new ResourceNotFoundException(id,Driver.class);
        driverRepository.deleteById(id);
    }
}
