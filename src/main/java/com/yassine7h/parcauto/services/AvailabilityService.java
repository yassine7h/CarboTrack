package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.repositories.HolidayRepository;
import com.yassine7h.parcauto.services.interfaces.IAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService implements IAvailabilityService {

    private final AffectationRepository affectationRepository;
    private final HolidayRepository holidayRepository;

    private boolean areIntervalsOverlap(Date start1, Date end1, Date start2, Date end2){
        return !end1.before(start2) && !start1.after(end2);
    }
    public boolean isVehicleAvailable(int idVehicle, Date startDate,Date endDate){
        List<Travel> travels=affectationRepository.findAllByVehicleId(idVehicle);
        for (Travel travel:travels) {
            var travelStart=travel.getStartDate();
            var travelEnd=travel.getEndDate();
            if(areIntervalsOverlap(travelStart, travelEnd, startDate, endDate))
                return false;
        }
        return true;
    }
    public boolean isDriverAvailable(int idDriver, Date startDate,Date endDate){

        List<Travel> travels=affectationRepository.findAllByDriverId(idDriver);
        List<Holiday> holidays=holidayRepository.findAllByDriverId(idDriver);
        for (Travel travel:travels) {
            var travelStart=travel.getStartDate();
            var travelEnd=travel.getEndDate();
            if(areIntervalsOverlap(travelStart, travelEnd, startDate, endDate))
                return false;
        }
        for (Holiday holiday:holidays) {
            var holidayStart=holiday.getStartDate();
            var holidayEnd=holiday.getEndDate();
            if(areIntervalsOverlap(holidayStart, holidayEnd, startDate, endDate))
                return false;
        }
        return true;
    }
}
