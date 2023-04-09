package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.DriverReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.models.Driver;

import java.util.List;

public interface IDriverService {
    public List<Driver> getAll();
    public List<DriverResDto> getAllDto();
    public Driver getById(int id);
    public DriverResDto getByIdDto(int id);
    public int add(Driver driver);
    public int addDto(DriverReqDto driver);

    public void update(Driver driver,int id);
    public void updateDto(DriverReqDto driver,int id);
    public void delete(int id);
}
