package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Vehicle;
import com.yassine7h.parcauto.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping(path = "")
    public ResponseEntity<List<Vehicle>> getVehicles(){
        return new ResponseEntity<>(vehicleService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id){
        return new ResponseEntity<>(vehicleService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveVehicle(@RequestBody Vehicle vehicle){
        int id=vehicleService.add(vehicle);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Vehicle added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/vehicles/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateVehicles(@PathVariable int id,@RequestBody Vehicle vehicle){
        vehicleService.update(vehicle);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Vehicle updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/vehicles/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteVehicle(@PathVariable int id){
        vehicleService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
