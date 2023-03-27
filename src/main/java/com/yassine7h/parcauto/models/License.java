package com.yassine7h.parcauto.models;

import com.fasterxml.jackson.annotation.*;
import com.yassine7h.parcauto.enums.LicenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;
    @ManyToOne
    @JoinColumn(name="driver_id")
    @JsonIgnoreProperties("licenses")
    private Driver driver;
}
