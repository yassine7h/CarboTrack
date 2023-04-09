package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class DriverResDto {
    private int id;
    private String firstName;
    private String lastName;
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String CIN;
    private Set<Integer> licenseIds;
    private Set<Integer> affectationIds;
}
