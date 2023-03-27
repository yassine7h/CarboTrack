package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.services.interfaces.IAffectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AffectationService  implements IAffectationService {
    private final AffectationRepository affectationRepository;

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
    public void update(Affectation affectation) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(affectation.getId());
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(affectation.getId(),Affectation.class);
        affectationRepository.save(affectation);
    }

    @Override
    public void delete(int id) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(id);
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(id,Affectation.class);
        affectationRepository.deleteById(id);
    }
}
