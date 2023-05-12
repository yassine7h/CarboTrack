package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.AffectationReqDto;
import com.yassine7h.parcauto.dtos.AffectationResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.AffectationMapper;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.repositories.DriverRepository;
import com.yassine7h.parcauto.repositories.TravelRepository;
import com.yassine7h.parcauto.repositories.VehicleRepository;
import com.yassine7h.parcauto.services.interfaces.IAffectationService;
import com.yassine7h.parcauto.services.interfaces.IAvailabilityService;
import com.yassine7h.parcauto.services.interfaces.IConformityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectationService  implements IAffectationService {
    private final AffectationRepository affectationRepository;
    private final TravelService travelService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final AffectationMapper affectationMapper;
    private final IConformityService conformityService;
    private final IAvailabilityService availabilityService;

    private boolean areAffectationFieldsValid(Affectation affectation){
        var fieldNotNull=affectation.getTravel()!=null
                        && affectation.getDriver()!=null
                        && affectation.getVehicle()!=null;
        if(!fieldNotNull) return false;
        if(isTravelAssigned(affectation.getTravel().getId())) return false;
        var travel=travelService.getById(affectation.getTravel().getId());
        var driver=driverService.getById(affectation.getDriver().getId());
        var vehicle=vehicleService.getById(affectation.getVehicle().getId());

        var driverConformityList=conformityService.getDriversByTravelId(travel.getId());
        var driverAvailabilityList=driverConformityList.stream().filter(
                driverItem-> availabilityService
                        .isDriverAvailable(driverItem.getId(),travel.getStartDate(),travel.getEndDate())
        ).toList();
        boolean isDriverValid=driverAvailabilityList.contains(driver);

        var vehicleConformityList=conformityService.getVehiclesByTravelId(travel.getId());
        var vehicleAvailabilityList=vehicleConformityList.stream().filter(
                vehicleItem-> availabilityService
                        .isDriverAvailable(vehicleItem.getId(),travel.getStartDate(),travel.getEndDate())
        ).toList();
        boolean isVehicleValid=vehicleAvailabilityList.contains(vehicle);
        return isDriverValid && isVehicleValid;
    }

    private boolean isTravelAssigned(int travelId){
        var travel= affectationRepository.isTravelAssigned(travelId).orElse(null);
        if(travel!=null) return true;
        else return false;
    }

    @Override
    public List<Affectation> getAll() {
        return affectationRepository.findAll();
    }

    @Override
    public Affectation getById(int id) {
        return affectationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Affectation.class));
    }

    @Override
    public int add(Affectation affectation) {
        if(!areAffectationFieldsValid(affectation))
            throw new RuntimeException("Driver and/or Vehicle is not assignable to travel");
        Optional<Affectation> affectationOptional=affectationRepository.findById(affectation.getId());
        if(affectationOptional.isPresent()) throw new ResourceExistException(affectation.getId(),Affectation.class);
        affectation.setAffectationDate(new Date(System.currentTimeMillis()));
        Affectation addedAffectation=affectationRepository.save(affectation);
        return addedAffectation.getId();
    }

    @Override
    public void update(Affectation affectation,int id) {
        if(!areAffectationFieldsValid(affectation))
            throw new RuntimeException("Driver and/or Vehicle is not assignable to travel");
        Optional<Affectation> affectationOptional=affectationRepository.findById(id);
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(affectation.getId(),Affectation.class);
        affectation.setId(id);
        affectation.setAffectationDate(new Date(System.currentTimeMillis()));
        affectationRepository.save(affectation);
    }

    @Override
    public void delete(int id) {
        Optional<Affectation> affectationOptional=affectationRepository.findById(id);
        if(!affectationOptional.isPresent()) throw new ResourceNotFoundException(id,Affectation.class);
        affectationRepository.deleteById(id);
    }

    @Override
    public List<AffectationResDto> getAllDto() {
        return  getAll().stream().map(i->affectationMapper.toAffectationResDto(i)).collect(Collectors.toList());
    }

    @Override
    public AffectationResDto getByIdDto(int id) {
        return affectationMapper.toAffectationResDto( getById(id));
    }

    @Override
    public int addDto(AffectationReqDto affectation) {
        return add(affectationMapper.toAffectation(affectation));
    }

    @Override
    public void updateDto(AffectationReqDto affectation,int id) {
        update(affectationMapper.toAffectation(affectation),id);
    }
}
