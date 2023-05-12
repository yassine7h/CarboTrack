package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.LicenseVehicleTypeConfig;

import java.util.List;

public interface ILicenseVehicleTypeConfigService {
    public List<LicenseVehicleTypeConfig> getAll();
    public LicenseVehicleTypeConfig getById(int id);
    public int add(LicenseVehicleTypeConfig driver);
    public void update(LicenseVehicleTypeConfig driver, int id);
    public void delete(int id);
}
