package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.LicenseVehicleTypeMap;
import com.yassine7h.parcauto.repositories.LicenseVehicleTypeMapRepository;
import com.yassine7h.parcauto.services.interfaces.ILicenseVehicleTypeMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseVehicleTypeMapService implements ILicenseVehicleTypeMapService {
    private final LicenseVehicleTypeMapRepository licenseVehicleTypeMapRepository;

    @Override
    public List<LicenseVehicleTypeMap> getAll() {
        return licenseVehicleTypeMapRepository.findAll();
    }

    @Override
    public LicenseVehicleTypeMap getById(int id) {
        return licenseVehicleTypeMapRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,LicenseVehicleTypeMap.class));
    }

    @Override
    public int add(LicenseVehicleTypeMap licenseVehicleTypeMap) {
        Optional<LicenseVehicleTypeMap> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(licenseVehicleTypeMap.getId());
        if(licenseVehicleTypeMapOptional.isPresent()) throw new ResourceExistException(licenseVehicleTypeMap.getId(),LicenseVehicleTypeMap.class);
        LicenseVehicleTypeMap addedLicenseVehicleTypeMap=licenseVehicleTypeMapRepository.save(licenseVehicleTypeMap);
        return addedLicenseVehicleTypeMap.getId();
    }

    @Override
    public void update(LicenseVehicleTypeMap licenseVehicleTypeMap) {
        Optional<LicenseVehicleTypeMap> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(licenseVehicleTypeMap.getId());
        if(!licenseVehicleTypeMapOptional.isPresent()) throw new ResourceNotFoundException(licenseVehicleTypeMap.getId(),LicenseVehicleTypeMap.class);
        licenseVehicleTypeMapRepository.save(licenseVehicleTypeMap);
    }

    @Override
    public void delete(int id) {
        Optional<LicenseVehicleTypeMap> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(id);
        if(!licenseVehicleTypeMapOptional.isPresent()) throw new ResourceNotFoundException(id,LicenseVehicleTypeMap.class);
        licenseVehicleTypeMapRepository.deleteById(id);
    }
}
