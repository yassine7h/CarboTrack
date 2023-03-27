package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.LicenseVehicleTypeMap;
import com.yassine7h.parcauto.services.LicenseVehicleTypeMapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/licenseVehicleTypeMaps")
public class LicenseVehicleTypeMapController {
    private final LicenseVehicleTypeMapService licenseVehicleTypeMapService;

    @GetMapping(path = "")
    public ResponseEntity<List<LicenseVehicleTypeMap>> getLicenseVehicleTypeMaps(){
        return new ResponseEntity<>(licenseVehicleTypeMapService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LicenseVehicleTypeMap> getLicenseVehicleTypeMap(@PathVariable int id){
        return new ResponseEntity<>(licenseVehicleTypeMapService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveLicenseVehicleTypeMap(@RequestBody LicenseVehicleTypeMap licenseVehicleTypeMap){
        int id=licenseVehicleTypeMapService.add(licenseVehicleTypeMap);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("LicenseVehicleTypeMap added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenseVehicleTypeMaps/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateLicenseVehicleTypeMaps(@PathVariable int id,@RequestBody LicenseVehicleTypeMap licenseVehicleTypeMap){
        licenseVehicleTypeMapService.update(licenseVehicleTypeMap);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("LicenseVehicleTypeMap updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenseVehicleTypeMaps/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteLicenseVehicleTypeMap(@PathVariable int id){
        licenseVehicleTypeMapService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
