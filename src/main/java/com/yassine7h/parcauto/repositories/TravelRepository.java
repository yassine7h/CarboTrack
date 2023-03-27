package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.enums.VehicleType;
import com.yassine7h.parcauto.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Integer> {
    @Query("SELECT e.vehicleType FROM Travel e WHERE e.id = ?1")
    VehicleType getById(int id);
}
