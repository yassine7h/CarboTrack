package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TravelReqDto {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    private VehicleType vehicleType;
}
