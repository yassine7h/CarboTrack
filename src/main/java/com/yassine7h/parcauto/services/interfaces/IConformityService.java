package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Vehicle;

import java.util.List;

public interface IConformityService {
    List<Driver> getDriversByTravelId(int idTravel);
    List<Vehicle> getVehiclesByTravelId(int idTravel);
    List<DriverResDto> getDriversByTravelIdDto(int idTravel);
    List<VehicleResDto> getVehiclesByTravelIdDto(int idTravel);
}
