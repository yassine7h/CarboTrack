package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.dtos.VehicleReqDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.models.Vehicle;
import com.yassine7h.parcauto.services.VehicleService;
import com.yassine7h.parcauto.services.interfaces.IVehicleService;
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
    private final IVehicleService vehicleService;

    @GetMapping(path = "")
    public ResponseEntity<List<VehicleResDto>> getVehicles(){
        return new ResponseEntity<>(vehicleService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleResDto> getVehicle(@PathVariable int id){
        return new ResponseEntity<>(vehicleService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveVehicle(@RequestBody VehicleReqDto vehicle){
        int id=vehicleService.addDto(vehicle);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Vehicle added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/vehicles/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateVehicles(@PathVariable int id,@RequestBody VehicleReqDto vehicle){
        vehicleService.updateDto(vehicle,id);
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
