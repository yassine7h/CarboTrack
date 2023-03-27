package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.services.AffectationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/affectations")
public class AffectationController {
    private final AffectationService affectationService;

    @GetMapping(path = "")
    public ResponseEntity<List<Affectation>> getAffectations(){
        return new ResponseEntity<>(affectationService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Affectation> getAffectation(@PathVariable int id){
        return new ResponseEntity<>(affectationService.getById(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveAffectation(@RequestBody Affectation affectation){
        int id=affectationService.add(affectation);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Affectation added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/affectations/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateAffectations(@PathVariable int id,@RequestBody Affectation affectation){
        affectationService.update(affectation);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Affectation updated successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/affectations/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAffectation(@PathVariable int id){
        affectationService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
