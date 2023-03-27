package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.License;

import java.util.List;

public interface ILicenseService {
    public List<License> getAll();
    public License getById(int id);
    public int add(License driver);
    public void update(License driver);
    public void delete(int id);
}
