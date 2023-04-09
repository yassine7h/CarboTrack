package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DriverReqDto {
    private String firstName;
    private String lastName;
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String CIN;
    private Set<Integer> licenseIds=new HashSet<>();
}
