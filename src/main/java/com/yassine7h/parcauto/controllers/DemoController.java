package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.License;
import com.yassine7h.parcauto.repositories.DriverRepository;
import com.yassine7h.parcauto.repositories.LicenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class DemoController {
    private final DriverRepository driverRepository;
    private final LicenseRepository licenseRepository;

    @GetMapping(path = "drivers")
    public List<Driver> getDrivers(){
        return driverRepository.findAll();
    }

    @GetMapping(path = "drivers/{id}")
    public Driver getDriver(@PathVariable int id){
        Driver driver=driverRepository.findById(id).orElseThrow(()->new RuntimeException("Entity doesnt exist"));
        return driver;
    }

    @PostMapping(path = "drivers")
    public ResponseEntity<String> setDrivers(@RequestBody Driver driver){
        log.info(driver.toString());
        driverRepository.save(driver);
        return ResponseEntity.ok("Entity added successfully");
    }

    @GetMapping(path = "licenses")
    public List<License> getLicenses(){
        return licenseRepository.findAll();
    }

    @PostMapping(path = "licenses")
    public ResponseEntity<String> setLicenses(@RequestBody License license){
        licenseRepository.save(license);
        return ResponseEntity.ok("Entity added successfully");
    }

    @PostMapping(path = "test")
    public ResponseEntity<String> tst(){
        return ResponseEntity.ok("Entity added successfully");
    }

}
