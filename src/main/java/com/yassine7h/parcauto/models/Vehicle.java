package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    private VehicleType vehicleType;
    @OneToMany(mappedBy = "vehicle")
    private Set<Affectation> affectations;
}
