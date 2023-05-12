package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.LicenseVehicleTypeConfig;
import com.yassine7h.parcauto.repositories.LicenseVehicleTypeMapRepository;
import com.yassine7h.parcauto.services.interfaces.ILicenseVehicleTypeConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseVehicleTypeConfigService implements ILicenseVehicleTypeConfigService {
    private final LicenseVehicleTypeMapRepository licenseVehicleTypeMapRepository;

    @Override
    public List<LicenseVehicleTypeConfig> getAll() {
        return licenseVehicleTypeMapRepository.findAll();
    }

    @Override
    public LicenseVehicleTypeConfig getById(int id) {
        return licenseVehicleTypeMapRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id, LicenseVehicleTypeConfig.class));
    }

    @Override
    public int add(LicenseVehicleTypeConfig licenseVehicleTypeConfig) {
        Optional<LicenseVehicleTypeConfig> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(licenseVehicleTypeConfig.getId());
        if(licenseVehicleTypeMapOptional.isPresent()) throw new ResourceExistException(licenseVehicleTypeConfig.getId(), LicenseVehicleTypeConfig.class);
        LicenseVehicleTypeConfig addedLicenseVehicleTypeConfig =licenseVehicleTypeMapRepository.save(licenseVehicleTypeConfig);
        return addedLicenseVehicleTypeConfig.getId();
    }

    @Override
    public void update(LicenseVehicleTypeConfig licenseVehicleTypeConfig, int id) {
        Optional<LicenseVehicleTypeConfig> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(id);
        if(!licenseVehicleTypeMapOptional.isPresent()) throw new ResourceNotFoundException(licenseVehicleTypeConfig.getId(), LicenseVehicleTypeConfig.class);
        licenseVehicleTypeConfig.setId(id);
        licenseVehicleTypeMapRepository.save(licenseVehicleTypeConfig);
    }

    @Override
    public void delete(int id) {
        Optional<LicenseVehicleTypeConfig> licenseVehicleTypeMapOptional=licenseVehicleTypeMapRepository.findById(id);
        if(!licenseVehicleTypeMapOptional.isPresent()) throw new ResourceNotFoundException(id, LicenseVehicleTypeConfig.class);
        licenseVehicleTypeMapRepository.deleteById(id);
    }
}
