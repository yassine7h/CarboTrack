package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class VehicleReqDto {
    private String matriculation;
    private String make;
    private String model;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    private VehicleType vehicleType;
}
