package com.yassine7h.parcauto.services.businesslogic;

import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.models.Travel;
import com.yassine7h.parcauto.repositories.AffectationRepository;
import com.yassine7h.parcauto.repositories.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AffectationRepository affectationRepository;
    private final HolidayRepository holidayRepository;

    private boolean areIntervalsNonOverlap(Date start1,Date end1,Date start2,Date end2){
        return  end1.before(start2)||start1.after(end2);
    }
    public boolean isVehicleAvailable(int idVehicle, Date startDate,Date endDate){
        List<Travel> travels=affectationRepository.findAllByVehicleId(idVehicle);
        for (Travel travel:travels) {
            var travelStart=travel.getStartDate();
            var travelEnd=travel.getEndDate();
            if(!areIntervalsNonOverlap(travelStart,travelEnd,startDate,endDate))
                return false;
        }
        return true;
    }
    public boolean isDriverAvailable(int idDriver, Date startDate,Date endDate){

        List<Travel> travels=affectationRepository.findAllByDriverId(idDriver);
        List<Holiday> holidays=holidayRepository.findAllByDriverId(idDriver);
        for (Travel travel:travels) {
            System.out.println(travel.getId());
            var travelStart=travel.getStartDate();
            var travelEnd=travel.getEndDate();
            if(!areIntervalsNonOverlap(travelStart,travelEnd,startDate,endDate))
                return false;
        }
        for (Holiday holiday:holidays) {
            var holidayStart=holiday.getStartDate();
            var holidayEnd=holiday.getEndDate();
            if(!areIntervalsNonOverlap(holidayStart,holidayEnd,startDate,endDate))
                return false;
        }
        return true;
    }
}
