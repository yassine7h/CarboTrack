package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yassine7h.parcauto.enums.TravelStatus;
import com.yassine7h.parcauto.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class TravelResDto {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    private VehicleType vehicleType;

    private Integer affectationId;
}
