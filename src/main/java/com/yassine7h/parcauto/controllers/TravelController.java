package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.*;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.services.TravelService;
import com.yassine7h.parcauto.services.interfaces.ITravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/travels")
public class TravelController {
    private final ITravelService travelService;

    @GetMapping(path = "")
    public ResponseEntity<List<TravelResDto>> getTravels(){
        return new ResponseEntity<>(travelService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TravelResDto> getTravel(@PathVariable int id){
        return new ResponseEntity<>(travelService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveTravel(@RequestBody TravelReqDto travel){
        int id=travelService.addDto(travel);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Travel added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/travels/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateTravels(@PathVariable int id,@RequestBody TravelReqDto travel){
        travelService.updateDto(travel,id);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Travel updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/travels/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTravel(@PathVariable int id){
        travelService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}/choice/drivers")
    public ResponseEntity<List<DriverResDto>> driverChoice(@PathVariable int id){
        return new ResponseEntity<>(travelService.driverChoice(id), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}/choice/vehicles")
    public ResponseEntity<List<VehicleResDto>> vehicleChoice(@PathVariable int id){
        return new ResponseEntity<>(travelService.vehicleChoice(id), HttpStatus.OK);
    }
}
