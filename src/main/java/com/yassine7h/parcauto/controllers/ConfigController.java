package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.LicenseVehicleTypeConfig;
import com.yassine7h.parcauto.services.interfaces.ILicenseVehicleTypeConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/config")
public class ConfigController {
    private final ILicenseVehicleTypeConfigService licenseVehicleTypeMapService;

    @GetMapping(path = "/licenseVehicleType")
    public ResponseEntity<List<LicenseVehicleTypeConfig>> getLicenseVehicleTypeMaps(){
        return new ResponseEntity<>(licenseVehicleTypeMapService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/licenseVehicleType/{id}")
    public ResponseEntity<LicenseVehicleTypeConfig> getLicenseVehicleTypeMap(@PathVariable int id){
        return new ResponseEntity<>(licenseVehicleTypeMapService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "/licenseVehicleType")
    public ResponseEntity<SuccessMessage> saveLicenseVehicleTypeMap(@RequestBody LicenseVehicleTypeConfig licenseVehicleTypeConfig){
        int id=licenseVehicleTypeMapService.add(licenseVehicleTypeConfig);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("LicenseVehicleTypeMap added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenseVehicleTypeMaps/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/licenseVehicleType/{id}")
    public ResponseEntity<SuccessMessage> updateLicenseVehicleTypeMaps(@PathVariable int id,@RequestBody LicenseVehicleTypeConfig licenseVehicleTypeConfig){
        licenseVehicleTypeMapService.update(licenseVehicleTypeConfig,id);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("LicenseVehicleTypeMap updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenseVehicleTypeMaps/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/licenseVehicleType/{id}")
    public ResponseEntity deleteLicenseVehicleTypeMap(@PathVariable int id){
        licenseVehicleTypeMapService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
