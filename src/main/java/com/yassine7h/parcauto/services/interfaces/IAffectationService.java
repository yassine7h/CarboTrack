package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.AffectationReqDto;
import com.yassine7h.parcauto.dtos.AffectationResDto;
import com.yassine7h.parcauto.models.Affectation;

import java.util.List;

public interface IAffectationService {
    public List<Affectation> getAll();
    public Affectation getById(int id);
    public int add(Affectation driver);
    public void update(Affectation driver,int id);
    public void delete(int id);
    List<AffectationResDto> getAllDto();
    AffectationResDto getByIdDto(int id);
    int addDto(AffectationReqDto admin);
    void updateDto(AffectationReqDto admin,int id);
}
