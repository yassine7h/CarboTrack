package com.yassine7h.parcauto.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yassine7h.parcauto.enums.VehicleType;
import com.yassine7h.parcauto.models.Affectation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;
@Getter
@Setter
public class VehicleResDto {
    private int id;
    private String matriculation;
    private String make;
    private String model;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    private VehicleType vehicleType;
    private Set<Integer> affectationIds;
}
