package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class AffectationResDto {
    private int id;
    private TravelResDto travel;
    private DriverResDto driver;
    private VehicleResDto vehicle;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;
}
