package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Integer> {
    @Query("SELECT e.travel FROM Affectation e WHERE e.vehicle.id = ?1")
    List<Travel> findAllByVehicleId(int vehicleId);
    @Query("SELECT e.travel FROM Affectation e WHERE e.driver.id = ?1")
    List<Travel> findAllByDriverId(int driverId);
}
