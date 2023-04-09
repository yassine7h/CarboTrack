package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.mappers.DriverMapper;
import com.yassine7h.parcauto.mappers.VehicleMapper;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Vehicle;
import com.yassine7h.parcauto.repositories.LicenseRepository;
import com.yassine7h.parcauto.repositories.LicenseVehicleTypeMapRepository;
import com.yassine7h.parcauto.repositories.TravelRepository;
import com.yassine7h.parcauto.repositories.VehicleRepository;
import com.yassine7h.parcauto.services.interfaces.IConformityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConformityService implements IConformityService {
    private final LicenseVehicleTypeMapRepository mapRepository;
    private final LicenseRepository licenseRepository;
    private final TravelRepository travelRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverMapper driverMapper;
    private final VehicleMapper vehicleMapper;

    @Override
    public List<Driver> getDriversByTravelId(int idTravel){
        var vehicleType=travelRepository.getById(idTravel);
        var licenseTypesList=mapRepository.getAllByVehicleType(vehicleType);
        return licenseRepository.getAllByLicenseTypeIn(licenseTypesList);
    }
    @Override
    public List<Vehicle> getVehiclesByTravelId(int idTravel){
        var vehicleType=travelRepository.getById(idTravel);
        return vehicleRepository.getAllByVehicleType(vehicleType);
    }

    @Override
    public List<DriverResDto> getDriversByTravelIdDto(int idTravel) {
        return getDriversByTravelId(idTravel).stream().map(i->driverMapper.toDriverResDto(i)).collect(Collectors.toList());
    }

    @Override
    public List<VehicleResDto> getVehiclesByTravelIdDto(int idTravel) {
        return getVehiclesByTravelId(idTravel).stream().map(i->vehicleMapper.toVehicleResDto(i)).collect(Collectors.toList());
    }

}
