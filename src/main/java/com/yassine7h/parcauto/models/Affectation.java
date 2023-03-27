package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnoreProperties("affectations")
    private Travel travel;
    @ManyToOne
    @JsonIgnoreProperties("affectations")
    private Driver driver;
    @ManyToOne
    @JsonIgnoreProperties("affectations")
    private Vehicle vehicle;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;
    private AffectationStatus status;
    enum AffectationStatus {
        SCHEDULED, AFFECTED, CANCELED, TRAVELING, TERMINATED
    }
}
