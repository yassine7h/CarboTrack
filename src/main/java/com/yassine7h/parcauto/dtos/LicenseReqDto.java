package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.LicenseType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class LicenseReqDto {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    private LicenseType licenseType;
    private int driverId;
}
