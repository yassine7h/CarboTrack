package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private TravelStatus travelStatus;

    @OneToMany(mappedBy = "travel")
    @JsonIgnoreProperties("travel")
    private Set<Affectation> affectations;

    enum TravelStatus {
        SCHEDULED, AFFECTED, CANCELED, TRAVELING, TERMINATED
    }
}
