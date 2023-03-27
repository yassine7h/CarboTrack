package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.License;
import com.yassine7h.parcauto.models.License;
import com.yassine7h.parcauto.repositories.LicenseRepository;
import com.yassine7h.parcauto.services.interfaces.ILicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService {
    private final LicenseRepository licenseRepository;

    @Override
    public List<License> getAll() {
        return licenseRepository.findAll();
    }

    @Override
    public License getById(int id) {
        return licenseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,License.class));
    }

    @Override
    public int add(License license) {
        Optional<License> licenseOptional=licenseRepository.findById(license.getId());
        if(licenseOptional.isPresent()) throw new ResourceExistException(license.getId(),License.class);
        License addedLicense=licenseRepository.save(license);
        return addedLicense.getId();
    }

    @Override
    public void update(License license) {
        Optional<License> licenseOptional=licenseRepository.findById(license.getId());
        if(!licenseOptional.isPresent()) throw new ResourceNotFoundException(license.getId(),License.class);
        licenseRepository.save(license);
    }

    @Override
    public void delete(int id) {
        Optional<License> licenseOptional=licenseRepository.findById(id);
        if(!licenseOptional.isPresent()) throw new ResourceNotFoundException(id,License.class);
        licenseRepository.deleteById(id);
    }
}
