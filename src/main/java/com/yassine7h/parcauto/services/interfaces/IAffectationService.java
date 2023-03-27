package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Affectation;

import java.util.List;

public interface IAffectationService {
    public List<Affectation> getAll();
    public Affectation getById(int id);
    public int add(Affectation driver);
    public void update(Affectation driver);
    public void delete(int id);
}
