package com.yassine7h.parcauto.models;

import com.yassine7h.parcauto.enums.LicenseType;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LicenseVehicleTypeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
