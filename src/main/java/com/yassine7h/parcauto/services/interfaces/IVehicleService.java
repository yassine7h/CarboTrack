package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Vehicle;

import java.util.List;

public interface IVehicleService {
    public List<Vehicle> getAll();
    public Vehicle getById(int id);
    public int add(Vehicle driver);
    public void update(Vehicle driver);
    public void delete(int id);
}
