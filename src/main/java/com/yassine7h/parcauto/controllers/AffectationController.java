package com.yassine7h.parcauto.controllers;

import com.yassine7h.parcauto.dtos.AffectationReqDto;
import com.yassine7h.parcauto.dtos.AffectationResDto;
import com.yassine7h.parcauto.dtos.SuccessMessage;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.services.AffectationService;
import com.yassine7h.parcauto.services.interfaces.IAffectationService;
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
    private final IAffectationService affectationService;

    @GetMapping(path = "")
    public ResponseEntity<List<AffectationResDto>> getAffectations(){
        return new ResponseEntity<>(affectationService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AffectationResDto> getAffectation(@PathVariable int id){
        return new ResponseEntity<>(affectationService.getByIdDto(id),HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<SuccessMessage> saveAffectation(@RequestBody AffectationReqDto affectation){
        int id=affectationService.addDto(affectation);
        var successMessage=new SuccessMessage();
        successMessage.setMessage("Affectation added successfully");
        successMessage.setResourceId(id);
        successMessage.setResourceUrl("/affectations/"+id);
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessage> updateAffectations(@PathVariable int id,@RequestBody AffectationReqDto affectation){
        affectationService.updateDto(affectation,id);
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
