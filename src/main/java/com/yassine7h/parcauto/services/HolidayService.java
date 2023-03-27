package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.repositories.HolidayRepository;
import com.yassine7h.parcauto.services.interfaces.IHolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HolidayService implements IHolidayService {
    private final HolidayRepository holidayRepository;

    @Override
    public List<Holiday> getAll() {
        return holidayRepository.findAll();
    }

    @Override
    public Holiday getById(int id) {
        return holidayRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Holiday.class));
    }

    @Override
    public int add(Holiday holiday) {
        Optional<Holiday> holidayOptional=holidayRepository.findById(holiday.getId());
        if(holidayOptional.isPresent()) throw new ResourceExistException(holiday.getId(),Holiday.class);
        Holiday addedHoliday=holidayRepository.save(holiday);
        return addedHoliday.getId();
    }

    @Override
    public void update(Holiday holiday) {
        System.out.println(holiday.getEndDate());
        Optional<Holiday> holidayOptional=holidayRepository.findById(holiday.getId());
        if(!holidayOptional.isPresent()) throw new ResourceNotFoundException(holiday.getId(),Holiday.class);
        holidayRepository.save(holiday);
    }

    @Override
    public void delete(int id) {
        Optional<Holiday> holidayOptional=holidayRepository.findById(id);
        if(!holidayOptional.isPresent()) throw new ResourceNotFoundException(id,Holiday.class);
        holidayRepository.deleteById(id);
    }
}
