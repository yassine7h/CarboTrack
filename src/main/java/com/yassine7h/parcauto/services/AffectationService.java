package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.AffectationReqDto;
import com.yassine7h.parcauto.dtos.AffectationResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.AffectationMapper;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.services.interfaces.IAffectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectationService  implements IAffectationService {
    private final AffectationRepository affectationRepository;
    private final AffectationMapper affectationMapper;

    @Override
    public List<Affectation> getAll() {
        return affectationRepository.findAll();
    }

    @Override
    public Affectation getById(int id) {
        return affectationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Affectation.class));
    }

    @Override
    public int add(Affectation affectation) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(affectation.getId());
        if(affectationOptional.isPresent()) throw new ResourceExistException(affectation.getId(),Affectation.class);
        Affectation addedAffectation=affectationRepository.save(affectation);
        return addedAffectation.getId();
    }

    @Override
    public void update(Affectation affectation,int id) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(id);
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(affectation.getId(),Affectation.class);
        affectation.setId(id);
        affectationRepository.save(affectation);
    }

    @Override
    public void delete(int id) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(id);
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(id,Affectation.class);
        affectationRepository.deleteById(id);
    }

    @Override
    public List<AffectationResDto> getAllDto() {
        return  getAll().stream().map(i->affectationMapper.toAffectationResDto(i)).collect(Collectors.toList());
    }

    @Override
    public AffectationResDto getByIdDto(int id) {
        return affectationMapper.toAffectationResDto( getById(id));
    }

    @Override
    public int addDto(AffectationReqDto affectation) {
        return add(affectationMapper.toAffectation(affectation));
    }

    @Override
    public void updateDto(AffectationReqDto affectation,int id) {
        update(affectationMapper.toAffectation(affectation),id);
    }
}
