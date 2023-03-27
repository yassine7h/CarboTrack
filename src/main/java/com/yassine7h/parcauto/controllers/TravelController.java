package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.services.TravelService;
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
    private final TravelService travelService;

    @GetMapping(path = "")
    public ResponseEntity<List<Travel>> getTravels(){
        return new ResponseEntity<>(travelService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Travel> getTravel(@PathVariable int id){
        return new ResponseEntity<>(travelService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveTravel(@RequestBody Travel travel){
        int id=travelService.add(travel);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Travel added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/travels/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateTravels(@PathVariable int id,@RequestBody Travel travel){
        travelService.update(travel);
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
}
