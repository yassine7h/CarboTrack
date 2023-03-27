package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.services.HolidayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping(path = "")
    public ResponseEntity<List<Holiday>> getHolidays(){

        List<Holiday> list =holidayService.getAll();
        System.out.println("from holiday controller getAll:  "+list.get(0).getEndDate());
        return new ResponseEntity<>(holidayService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Holiday> getHoliday(@PathVariable int id){

        return new ResponseEntity<>(holidayService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveHoliday(@RequestBody Holiday holiday){
        int id=holidayService.add(holiday);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Holiday added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/holidays/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateHolidays(@PathVariable int id,@RequestBody Holiday holiday){
       // holiday.setId(id);
        holidayService.update(holiday);
        System.out.println(holiday.getEndDate());
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Holiday updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/holidays/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteHoliday(@PathVariable int id){
        holidayService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
