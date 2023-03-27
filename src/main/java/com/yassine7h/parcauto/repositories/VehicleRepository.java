package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.enums.VehicleType;
import com.yassine7h.parcauto.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    public List<Vehicle> getAllByVehicleType(VehicleType vehicleType);
}
