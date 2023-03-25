package com.yassine7h.parcauto.models;

import com.yassine7h.parcauto.enums.LicenseType;
import com.yassine7h.parcauto.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LicenseVehicleTypeMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LicenseType licenseType;
    private VehicleType vehicleType;
}
