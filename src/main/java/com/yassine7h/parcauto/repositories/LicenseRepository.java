package com.yassine7h.parcauto.repositories;

import com.yassine7h.parcauto.enums.LicenseType;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License,Integer> {
    @Query("SELECT e.driver FROM License e WHERE e.licenseType in ?1")
    List<Driver> getAllByLicenseTypeIn(List<LicenseType> licenseTypesList);
}
