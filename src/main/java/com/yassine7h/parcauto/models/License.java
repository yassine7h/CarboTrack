package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yassine7h.parcauto.enums.LicenseType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;
    private LicenseType licenseType;
}
