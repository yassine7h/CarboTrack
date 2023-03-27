package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Integer> {
    @Query("SELECT e FROM Holiday e WHERE e.driver.id = ?1")
    List<Holiday> findAllByDriverId(int idDriver);
}
