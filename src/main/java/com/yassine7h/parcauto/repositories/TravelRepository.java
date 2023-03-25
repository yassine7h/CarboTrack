package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Integer> {
}
