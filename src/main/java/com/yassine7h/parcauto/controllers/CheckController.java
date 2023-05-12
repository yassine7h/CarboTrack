/*
package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.services.interfaces.IAvailabilityService;
import com.yassine7h.parcauto.services.interfaces.IConformityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class CheckController {
    private final IConformityService conformityService;
    private final IAvailabilityService availabilityService;

    @GetMapping(path = "/driversForTravel/{id}")
    public ResponseEntity<List<DriverResDto>> getDriversForTravel(@PathVariable int id){
        return new ResponseEntity<>(conformityService.getDriversByTravelIdDto(id), HttpStatus.OK);
    }
    @GetMapping(path = "/vehiclesForTravel/{id}")
    public ResponseEntity<List<VehicleResDto>> getVehiclesByTravelId(@PathVariable int id){
        return new ResponseEntity<>(conformityService.getVehiclesByTravelIdDto(id), HttpStatus.OK);
    }
    @GetMapping(path = "/isVehicleAvailable/{id}")
    public ResponseEntity<Boolean> isVehicleAvailable(@PathVariable int id,
                                                      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date start,
                                                      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date end){
        return new ResponseEntity<>(availabilityService.isVehicleAvailable(id, new Date(start.getTime()),
                                                                               new Date(end.getTime())),HttpStatus.OK);
    }
    @GetMapping(path = "/isDriverAvailable/{id}")
    public ResponseEntity<Boolean> isDriverAvailable(@PathVariable int id,
                                                     @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date start,
                                                     @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date end){

        return new ResponseEntity<>(availabilityService.isDriverAvailable(id,new Date(start.getTime()),
                                                                            new Date(end.getTime())),HttpStatus.OK);
    }

}
*/
