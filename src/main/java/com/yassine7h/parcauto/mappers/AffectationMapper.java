package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.*;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.models.Vehicle;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;




@Mapper(componentModel = "spring")
public abstract class AffectationMapper {
    @Autowired
    private  TravelMapper travelMapper;
    @Autowired
    private  VehicleMapper vehicleMapper;
    @Autowired
    private  DriverMapper driverMapper;


    @Mapping(target = "travel",expression = "java(setTravelResDto(affectation.getTravel()))")
    @Mapping(target = "driver",expression = "java(setDriverResDto(affectation.getDriver()))")
    @Mapping(target = "vehicle",expression = "java(setVehicleResDto(affectation.getVehicle()))")
    public abstract AffectationResDto toAffectationResDto(Affectation affectation);

    @Mapping(target = "travel",expression = "java(setTravel(affectationReqDto.getTravelId()))")
    @Mapping(target = "driver",expression = "java(setDriver(affectationReqDto.getDriverId()))")
    @Mapping(target = "vehicle",expression = "java(setVehicle(affectationReqDto.getVehicleId()))")
    public abstract Affectation toAffectation(AffectationReqDto affectationReqDto);

    public DriverResDto setDriverResDto(Driver driver) {
        return driverMapper.toDriverResDto(driver);
    }
    public TravelResDto setTravelResDto(Travel travel) {
        return travelMapper.toTravelResDto(travel);
    }
    public VehicleResDto setVehicleResDto(Vehicle vehicle) {
        return vehicleMapper.toVehicleResDto(vehicle);
    }
    public Driver setDriver(int driverId) {
        var driver=new Driver();
        driver.setId(driverId);
        return driver;
    }
    public Travel setTravel(int travelId) {
        var travel=new Travel();
        travel.setId(travelId);
        return travel;
    }
    public Vehicle setVehicle(int vehicleId) {
        var vehicle=new Vehicle();
        vehicle.setId(vehicleId);
        return vehicle;
    }
}
