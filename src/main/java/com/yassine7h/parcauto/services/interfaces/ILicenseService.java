package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.LicenseReqDto;
import com.yassine7h.parcauto.dtos.LicenseResDto;
import com.yassine7h.parcauto.models.License;

import java.util.List;

public interface ILicenseService {
    public List<License> getAll();
    public License getById(int id);
    public int add(License driver);
    public void update(License driver,int id);
    public void delete(int id);
    List<LicenseResDto> getAllDto();
    LicenseResDto getByIdDto(int id);
    int addDto(LicenseReqDto admin);
    void updateDto(LicenseReqDto admin,int id);
}
