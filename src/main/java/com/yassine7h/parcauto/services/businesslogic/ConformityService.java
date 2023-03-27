package com.yassine7h.parcauto.services.businesslogic;

import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Vehicle;
import com.yassine7h.parcauto.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConformityService {
    private final LicenseVehicleTypeMapRepository mapRepository;
    private final LicenseRepository licenseRepository;
    private final TravelRepository travelRepository;
    private final VehicleRepository vehicleRepository;

    public List<Driver> getDriversByTravelId(int idTravel){
        var vehicleType=travelRepository.getById(idTravel);
        var licenseTypesList=mapRepository.getAllByVehicleType(vehicleType);
        return licenseRepository.getAllByLicenseTypeIn(licenseTypesList);
    }
    public List<Vehicle> getVehiclesByTravelId(int idTravel){
        var vehicleType=travelRepository.getById(idTravel);
        return vehicleRepository.getAllByVehicleType(vehicleType);
    }
}
