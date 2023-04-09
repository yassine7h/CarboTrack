package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.TravelReqDto;
import com.yassine7h.parcauto.dtos.TravelResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.TravelMapper;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.repositories.TravelRepository;
import com.yassine7h.parcauto.services.interfaces.ITravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService implements ITravelService {
    private final TravelRepository travelRepository;
    private final TravelMapper travelMapper;

    @Override
    public List<Travel> getAll() {
        return travelRepository.findAll();
    }

    @Override
    public Travel getById(int id) {
        return travelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Travel.class));
    }

    @Override
    public int add(Travel travel) {
        Optional<Travel> travelOptional=travelRepository.findById(travel.getId());
        if(travelOptional.isPresent()) throw new ResourceExistException(travel.getId(),Travel.class);
        Travel addedTravel=travelRepository.save(travel);
        return addedTravel.getId();
    }

    @Override
    public void update(Travel travel,int id) {
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
}
