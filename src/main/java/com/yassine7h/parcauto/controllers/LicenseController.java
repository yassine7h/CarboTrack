package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.LicenseReqDto;
import com.yassine7h.parcauto.dtos.LicenseResDto;
import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.License;
import com.yassine7h.parcauto.services.LicenseService;
import com.yassine7h.parcauto.services.interfaces.ILicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/licenses")
public class LicenseController {
    private final ILicenseService licenseService;

    @GetMapping(path = "")
    public ResponseEntity<List<LicenseResDto>> getLicenses(){
        return new ResponseEntity<>(licenseService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LicenseResDto> getLicense(@PathVariable int id){
        return new ResponseEntity<>(licenseService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveLicense(@RequestBody LicenseReqDto license){
        int id=licenseService.addDto(license);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("License added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenses/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateLicenses(@PathVariable int id,@RequestBody LicenseReqDto license){
        licenseService.updateDto(license,id);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("License updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/licenses/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteLicense(@PathVariable int id){
        licenseService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
