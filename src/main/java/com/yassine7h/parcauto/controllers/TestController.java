package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.models.*;
import com.yassine7h.parcauto.services.businesslogic.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class TestController {
    private final ConformityService conformityService;
    private final AvailabilityService availabilityService;

    @GetMapping(path = "/driversForTravel/{id}")
    public ResponseEntity<List<Driver>> getDriversForTravel(@PathVariable int id){
        return new ResponseEntity<>(conformityService.getDriversByTravelId(id), HttpStatus.OK);
    }
    @GetMapping(path = "/vehiclesForTravel/{id}")
    public ResponseEntity<List<Vehicle>> getVehiclesByTravelId(@PathVariable int id){
        return new ResponseEntity<>(conformityService.getVehiclesByTravelId(id), HttpStatus.OK);
    }
    @GetMapping(path = "/isVehicleAvailable/{id}")
    public ResponseEntity<Boolean> isVehicleAvailable(@PathVariable int id,
                                                      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date start,
                                                      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") java.util.Date end){
        return new ResponseEntity<>(availabilityService.isVehicleAvailable(id, new Date(start.getTime()),
                                                                               new Date(end.getTime())),HttpStatus.OK);
    }
    @GetMapping(path = "/isDriverAvailable/{id}")
    public ResponseEntity<Boolean> isDriverAvailable(@PathVariable int id,@RequestParam String start,@RequestParam String end){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Date startDate =Date.valueOf(LocalDate.parse(start, dateFormat));
        Date endDate =Date.valueOf(LocalDate.parse(end, dateFormat));
        return new ResponseEntity<>(availabilityService.isDriverAvailable(id,startDate,endDate), HttpStatus.OK);
    }
}
