package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String CIN;
    private DriverStatus driverStatus;
    @OneToMany(mappedBy = "driver",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("driver")
    private Set<License> licenses;
    @OneToOne
    @JsonIgnore
    private Account account;
    @OneToMany(mappedBy = "driver")
    @JsonIgnoreProperties("driver")
    private Set<Affectation> affectations;
    enum DriverStatus {
        NOT_AFFECTED, AFFECTED, TRAVELING, ON_LEAVE
    }
}
