package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.DriverReqDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.models.Driver;
import com.yassine7h.parcauto.models.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DriverMapper {
    @Mapping(target = "licenseIds",expression = "java(setLicensesIds(driver.getLicenses()))")
    @Mapping(target = "affectationIds",expression = "java(setAffectationIds(driver.getAffectations()))")
    public abstract DriverResDto toDriverResDto(Driver driver);
    @Mapping(target = "licenses",expression = "java(setLicenses(driver.getLicenseIds()))")
    public abstract Driver toDriver(DriverReqDto driver);
    public Set<Integer> setAffectationIds(Set<Affectation> affectations) {
        return affectations.stream().map(Affectation::getId).collect(Collectors.toSet());
    }
    public Set<Integer> setLicensesIds(Set<License> licenses) {
        return licenses.stream().map(License::getId).collect(Collectors.toSet());
    }
    public Set<License> setLicenses(Set<Integer> licenseIds) {
        return licenseIds.stream().map(id->{
            var l=new License();
            l.setId(id);
            return l;
        }).collect(Collectors.toSet());
    }
}
