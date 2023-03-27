package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Driver;

import java.util.List;

public interface IDriverService {
    public List<Driver> getAll();
    public Driver getById(int id);
    public int add(Driver driver);
    public void update(Driver driver);
    public void delete(int id);
}
