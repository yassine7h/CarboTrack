package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.HolidayReqDto;
import com.yassine7h.parcauto.dtos.HolidayResDto;
import com.yassine7h.parcauto.dtos.HolidayReqDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.HolidayMapper;
import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.repositories.HolidayRepository;
import com.yassine7h.parcauto.services.interfaces.IHolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HolidayService implements IHolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

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
    public void update(Holiday holiday,int id) {
        System.out.println(holiday.getEndDate());
        Optional<Holiday> holidayOptional=holidayRepository.findById(id);
        if(!holidayOptional.isPresent()) throw new ResourceNotFoundException(holiday.getId(),Holiday.class);
        holiday.setId(id);
        holidayRepository.save(holiday);
    }

    @Override
    public void delete(int id) {
        Optional<Holiday> holidayOptional=holidayRepository.findById(id);
        if(!holidayOptional.isPresent()) throw new ResourceNotFoundException(id,Holiday.class);
        holidayRepository.deleteById(id);
    }

    @Override
    public List<HolidayResDto> getAllDto() {
        return  getAll().stream().map(i->holidayMapper.toHolidayResDto(i)).collect(Collectors.toList());
    }

    @Override
    public HolidayResDto getByIdDto(int id) {
        return holidayMapper.toHolidayResDto( getById(id));
    }

    @Override
    public int addDto(HolidayReqDto holiday) {
        return add(holidayMapper.toHoliday(holiday));
    }

    @Override
    public void updateDto(HolidayReqDto holiday,int id) {
        update(holidayMapper.toHoliday(holiday),id);
    }
}
