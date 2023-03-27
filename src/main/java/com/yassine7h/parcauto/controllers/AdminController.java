package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Admin;
import com.yassine7h.parcauto.services.AdminService;
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
    private final AdminService adminService;

    @GetMapping(path = "")
    public ResponseEntity<List<Admin>> getAdmins(){
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int id){
        return new ResponseEntity<>(adminService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveAdmin(@RequestBody Admin admin){
        int id=adminService.add(admin);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Admin added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/admins/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateAdmins(@PathVariable int id,@RequestBody Admin admin){
        adminService.update(admin);
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
