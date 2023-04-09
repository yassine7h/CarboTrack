package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.AdminReqDto;
import com.yassine7h.parcauto.dtos.AdminResDto;
import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Admin;
import com.yassine7h.parcauto.services.AdminService;
import com.yassine7h.parcauto.services.interfaces.IAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admins")
public class AdminController {
    private final IAdminService adminService;

    @GetMapping(path = "")
    public ResponseEntity<List<AdminResDto>> getAdmins(){
        return new ResponseEntity<>(adminService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AdminResDto> getAdmin(@PathVariable int id){
        return new ResponseEntity<>(adminService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveAdmin(@RequestBody AdminReqDto admin){
        int id=adminService.addDto(admin);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Admin added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/admins/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateAdmins(@PathVariable int id,@RequestBody AdminReqDto admin){
        adminService.updateDto(admin,id);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Admin updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/admins/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAdmin(@PathVariable int id){
        adminService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
