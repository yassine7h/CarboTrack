package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.DriverReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.services.DriverService;
import com.yassine7h.parcauto.services.interfaces.IDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/drivers")
public class DriverController {

    private final IDriverService driverService;

    @GetMapping(path = "")
    public ResponseEntity<List<DriverResDto>> getDrivers(){
        return new ResponseEntity<>(driverService.getAllDto(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DriverResDto> getDriver(@PathVariable int id){
        return new ResponseEntity<>(driverService.getByIdDto(id),HttpStatus.OK);
    }


    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveDriver(@RequestBody DriverReqDto driver){
        int id=driverService.addDto(driver);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Driver added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/drivers/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateDrivers(@PathVariable int id,@RequestBody DriverReqDto driver){
        driverService.updateDto(driver,id);
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
