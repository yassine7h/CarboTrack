package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.LicenseVehicleTypeMap;

import java.util.List;

public interface ILicenseVehicleTypeMapService {
    public List<LicenseVehicleTypeMap> getAll();
    public LicenseVehicleTypeMap getById(int id);
    public int add(LicenseVehicleTypeMap driver);
    public void update(LicenseVehicleTypeMap driver,int id);
    public void delete(int id);
}
