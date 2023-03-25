package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Integer> {
}
