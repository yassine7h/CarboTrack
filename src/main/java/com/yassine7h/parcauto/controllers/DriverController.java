package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/drivers")
public class DriverController {

    private final DriverService driverService;

    @GetMapping(path = "")
    public ResponseEntity<List<Driver>> getDrivers(){
        return new ResponseEntity<>(driverService.getAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable int id){
        return new ResponseEntity<>(driverService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveDriver(@RequestBody Driver driver){
        int id=driverService.add(driver);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Driver added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/drivers/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateDrivers(@PathVariable int id,@RequestBody Driver driver){
        driverService.update(driver);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Driver updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/drivers/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteDriver(@PathVariable int id){
        driverService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
