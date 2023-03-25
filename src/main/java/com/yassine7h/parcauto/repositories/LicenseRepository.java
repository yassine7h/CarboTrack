package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.models.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License,Integer> {
}
