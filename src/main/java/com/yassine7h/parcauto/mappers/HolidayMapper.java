package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.HolidayReqDto;
import com.yassine7h.parcauto.dtos.HolidayResDto;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.Holiday;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class HolidayMapper {
    @Autowired
    private  DriverMapper driverMapper;

    @Mapping(target = "driver",expression = "java(setDriverResDto(holiday.getDriver()))")
    public abstract HolidayResDto toHolidayResDto(Holiday holiday);


    @Mapping(target = "driver",expression = "java(setDriver(holidayReqDto.getDriverId()))")
    public abstract Holiday toHoliday(HolidayReqDto holidayReqDto);

    public DriverResDto setDriverResDto(Driver driver) {
        return driverMapper.toDriverResDto(driver);
    }
    public Driver setDriver(int driverId) {
        var driver=new Driver();
        driver.setId(driverId);
        return driver;
    }
}
