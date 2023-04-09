package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.HolidayReqDto;
import com.yassine7h.parcauto.dtos.HolidayResDto;
import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.services.HolidayService;
import com.yassine7h.parcauto.services.interfaces.IHolidayService;
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
    private final IHolidayService holidayService;

    @GetMapping(path = "")
    public ResponseEntity<List<HolidayResDto>> getHolidays(){
        List<Holiday> list =holidayService.getAll();
        return new ResponseEntity<>(holidayService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HolidayResDto> getHoliday(@PathVariable int id){
        return new ResponseEntity<>(holidayService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveHoliday(@RequestBody HolidayReqDto holiday){
        int id=holidayService.addDto(holiday);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Holiday added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/holidays/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateHolidays(@PathVariable int id,@RequestBody HolidayReqDto holiday){
        holidayService.updateDto(holiday,id);
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
