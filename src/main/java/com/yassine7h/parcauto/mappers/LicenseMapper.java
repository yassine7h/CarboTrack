package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.*;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LicenseMapper {
    @Autowired
    private  DriverMapper driverMapper;

    @Mapping(target = "driver",expression = "java(setDriverResDto(license.getDriver()))")
    public abstract LicenseResDto toLicenseResDto(License license);


    @Mapping(target = "driver",expression = "java(setDriver(licenseReqDto.getDriverId()))")
    public abstract License toLicense(LicenseReqDto licenseReqDto);

    public DriverResDto setDriverResDto(Driver driver) {
        return driverMapper.toDriverResDto(driver);
    }
    public Driver setDriver(int driverId) {
        var driver=new Driver();
        driver.setId(driverId);
        return driver;
    }
}
