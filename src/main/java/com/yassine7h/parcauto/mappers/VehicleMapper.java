package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.AdminReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.VehicleReqDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.models.Admin;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class VehicleMapper {
    @Mapping(target = "affectationIds",expression = "java(setAffectationIds(vehicle.getAffectations()))")
    public abstract VehicleResDto toVehicleResDto(Vehicle vehicle);

    public abstract Vehicle toVehicle(VehicleReqDto vehicleReqDtoReqDto);

    public Set<Integer> setAffectationIds(Set<Affectation> affectations) {
        return affectations.stream().map(Affectation::getId).collect(Collectors.toSet());
    }
}
