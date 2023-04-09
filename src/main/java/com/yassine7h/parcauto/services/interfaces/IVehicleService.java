package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.VehicleReqDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.models.Vehicle;

import java.util.List;

public interface IVehicleService {
    public List<Vehicle> getAll();
    public Vehicle getById(int id);
    public int add(Vehicle driver);
    public void update(Vehicle driver,int id);
    public void delete(int id);
    List<VehicleResDto> getAllDto();
    VehicleResDto getByIdDto(int id);
    int addDto(VehicleReqDto admin);
    void updateDto(VehicleReqDto admin,int id);
}
