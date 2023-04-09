package com.yassine7h.parcauto.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class AffectationReqDto {
    private int travelId;
    private int driverId;
    private int vehicleId;
  /*  @JsonFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;*/
}
