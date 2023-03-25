package com.yassine7h.parcauto.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String matriculation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String CIN;
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private Set<License> licenses;
    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "driver")
    private Set<Affectation> affectations;


}
