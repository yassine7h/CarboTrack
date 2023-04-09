package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class AdminResDto {
    private int id;
    private String firstName;
    private String lastName;
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String CIN;
}
