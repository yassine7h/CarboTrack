package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.TravelReqDto;
import com.yassine7h.parcauto.dtos.TravelResDto;
import com.yassine7h.parcauto.models.Affectation;
import com.yassine7h.parcauto.models.Travel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class TravelMapper {
    @Mapping(target = "affectationId",expression = "java(setAffectationId(travel.getAffectation()))")
    public abstract TravelResDto toTravelResDto(Travel travel);
    public abstract Travel toTravel(TravelReqDto travelReqDto);

    public Integer setAffectationId(Affectation affectation) {
        if(affectation!=null)
            return affectation.getId();
        return null;
    }
}
