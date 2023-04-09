package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String matriculation;
    private String make;
    private String model;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


    @OneToMany(mappedBy = "vehicle")
    @JsonIgnoreProperties("vehicle")
    private Set<Affectation> affectations;

/*    private VehicleStatus vehicleStatus;
    enum VehicleStatus {
        NOT_AFFECTED, AFFECTED, TRAVELING, NOT_OPERATIONAL
    }*/
}
