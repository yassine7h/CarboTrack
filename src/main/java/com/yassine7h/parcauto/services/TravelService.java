package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.dtos.TravelReqDto;
import com.yassine7h.parcauto.dtos.TravelResDto;
import com.yassine7h.parcauto.dtos.VehicleResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.DriverMapper;
import com.yassine7h.parcauto.mappers.TravelMapper;
import com.yassine7h.parcauto.mappers.VehicleMapper;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.repositories.TravelRepository;
import com.yassine7h.parcauto.services.interfaces.IAvailabilityService;
import com.yassine7h.parcauto.services.interfaces.IConformityService;
import com.yassine7h.parcauto.services.interfaces.ITravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService implements ITravelService {
    private final TravelRepository travelRepository;
    private final IAvailabilityService availabilityService;
    private final IConformityService conformityService;
    private final TravelMapper travelMapper;
    private final DriverMapper driverMapper;
    private final VehicleMapper vehicleMapper;

    private boolean areTravelDatesValid(Travel travel){
        return (new Date(System.currentTimeMillis())).compareTo(travel.getStartDate())<=0
                && travel.getStartDate().compareTo(travel.getEndDate())<=0;
    }

    @Override
    public List<Travel> getAll() {
        return travelRepository.findAll();
    }

    @Override
    public Travel getById(int id) {
        return travelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Travel.class));
    }

    @Override
    public int add(Travel travel){
        if (!areTravelDatesValid(travel))
            throw new RuntimeException("Travel has no valid dates");
        Optional<Travel> travelOptional=travelRepository.findById(travel.getId());
        if(travelOptional.isPresent()) throw new ResourceExistException(travel.getId(),Travel.class);
        Travel addedTravel=travelRepository.save(travel);
        return addedTravel.getId();
    }

    @Override
    public void update(Travel travel,int id) {
        if (!areTravelDatesValid(travel))
            throw new RuntimeException("Travel has no valid dates");
        Optional<Travel> travelOptional=travelRepository.findById( id);
        if(!travelOptional.isPresent()) throw new ResourceNotFoundException(travel.getId(),Travel.class);
        travel.setId(id);
        travelRepository.save(travel);
    }

    @Override
    public void delete(int id) {
        Optional<Travel> travelOptional=travelRepository.findById(id);
        if(!travelOptional.isPresent()) throw new ResourceNotFoundException(id,Travel.class);
        travelRepository.deleteById(id);
    }

    @Override
    public List<TravelResDto> getAllDto() {
        return  getAll().stream().map(i->travelMapper.toTravelResDto(i)).collect(Collectors.toList());
    }

    @Override
    public TravelResDto getByIdDto(int id) {
        return travelMapper.toTravelResDto( getById(id));
    }

    @Override
    public int addDto(TravelReqDto travel) {
        return add(travelMapper.toTravel(travel));
    }

    @Override
    public void updateDto(TravelReqDto travel,int id) {
        update(travelMapper.toTravel(travel), id);
    }

    @Override
    public List<DriverResDto> driverChoice(int travelId){
        var travel=getById(travelId);
        var conformityList=conformityService.getDriversByTravelId(travelId);
        var availabilityList=conformityList.stream().filter(
                driver-> availabilityService
                        .isDriverAvailable(driver.getId(),travel.getStartDate(),travel.getEndDate())
        ).toList();
        return availabilityList.stream().map(driver->driverMapper.toDriverResDto(driver)).toList();
    }

    @Override
    public List<VehicleResDto> vehicleChoice(int travelId){
        var travel=getById(travelId);
        var conformityList=conformityService.getVehiclesByTravelId(travelId);
        var availabilityList=conformityList.stream().filter(
                vehicle-> availabilityService
                        .isDriverAvailable(vehicle.getId(),travel.getStartDate(),travel.getEndDate())
        ).toList();
        return availabilityList.stream().map(vehicle->vehicleMapper.toVehicleResDto(vehicle)).toList();
    }
}
