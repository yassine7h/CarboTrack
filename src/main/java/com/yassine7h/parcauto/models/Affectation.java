package com.yassine7h.parcauto.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Travel travel;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Vehicle vehicle;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;
    private AffectationStatus status;
    enum AffectationStatus {
        SCHEDULED, TRAVELING, TERMINATED
    }
}
