package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.LicenseReqDto;
import com.yassine7h.parcauto.dtos.LicenseResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.LicenseMapper;
import com.yassine7h.parcauto.models.License;
import com.yassine7h.parcauto.repositories.LicenseRepository;
import com.yassine7h.parcauto.services.interfaces.ILicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService {
    private final LicenseRepository licenseRepository;
    private final LicenseMapper licenseMapper;

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
    public void update(License license,int id) {
        Optional<License> licenseOptional=licenseRepository.findById(id);
        if(!licenseOptional.isPresent()) throw new ResourceNotFoundException(license.getId(),License.class);
        license.setId(id);
        licenseRepository.save(license);
    }

    @Override
    public void delete(int id) {
        Optional<License> licenseOptional=licenseRepository.findById(id);
        if(!licenseOptional.isPresent()) throw new ResourceNotFoundException(id,License.class);
        licenseRepository.deleteById(id);
    }

    @Override
    public List<LicenseResDto> getAllDto() {
        return  getAll().stream().map(i-> licenseMapper.toLicenseResDto(i)).collect(Collectors.toList());
    }

    @Override
    public LicenseResDto getByIdDto(int id) {
        return licenseMapper.toLicenseResDto( getById(id));
    }

    @Override
    public int addDto(LicenseReqDto license) {
        return add(licenseMapper.toLicense(license));
    }

    @Override
    public void updateDto(LicenseReqDto license,int id) {
        update(licenseMapper.toLicense(license), id);
    }
}
