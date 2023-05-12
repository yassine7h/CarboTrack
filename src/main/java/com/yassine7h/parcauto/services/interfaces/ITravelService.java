package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.TravelReqDto;
import com.yassine7h.parcauto.dtos.TravelResDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.models.Travel;

import java.util.List;

public interface ITravelService {
    public List<Travel> getAll();
    public Travel getById(int id);
    public int add(Travel driver) throws Exception;
    public void update(Travel driver,int id);
    public void delete(int id);
    List<TravelResDto> getAllDto();
    TravelResDto getByIdDto(int id);
    int addDto(TravelReqDto admin);
    void updateDto(TravelReqDto admin,int id);
    List<DriverResDto> driverChoice(int travelId);
    List<VehicleResDto> vehicleChoice(int travelId);
}
