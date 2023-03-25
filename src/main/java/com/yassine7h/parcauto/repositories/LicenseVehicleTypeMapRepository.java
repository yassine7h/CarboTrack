package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.LicenseVehicleTypeMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseVehicleTypeMapRepository extends JpaRepository<LicenseVehicleTypeMap,Integer> {
}
