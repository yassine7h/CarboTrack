package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Travel;

import java.util.List;

public interface ITravelService {
    public List<Travel> getAll();
    public Travel getById(int id);
    public int add(Travel driver);
    public void update(Travel driver);
    public void delete(int id);
}
