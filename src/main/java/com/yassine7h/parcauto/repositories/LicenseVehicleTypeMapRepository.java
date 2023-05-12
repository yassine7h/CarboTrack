package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.enums.LicenseType;
import com.yassine7h.parcauto.enums.VehicleType;
import com.yassine7h.parcauto.models.LicenseVehicleTypeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseVehicleTypeMapRepository extends JpaRepository<LicenseVehicleTypeConfig,Integer> {
    @Query("SELECT e.licenseType FROM LicenseVehicleTypeConfig e WHERE e.vehicleType = ?1")
    List<LicenseType> getAllByVehicleType(VehicleType vehicleType);
}
