package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.DriverReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.models.Driver;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDriverService {
     List<Driver> getAll();
     List<DriverResDto> getAllDto();
     Driver getById(int id);
     DriverResDto getByIdDto(int id);
     int add(Driver driver);
     int addDto(DriverReqDto driver);

     void update(Driver driver,int id);
     void updateDto(DriverReqDto driver,int id);
     void delete(int id);
}
