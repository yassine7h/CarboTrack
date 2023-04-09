package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.HolidayReqDto;
import com.yassine7h.parcauto.dtos.HolidayResDto;
import com.yassine7h.parcauto.models.Holiday;
import com.yassine7h.parcauto.models.Holiday;

import java.util.List;

public interface IHolidayService {
    public List<Holiday> getAll();
    public Holiday getById(int id);
    public int add(Holiday driver);
    public void update(Holiday driver,int id);
    public void delete(int id);
    List<HolidayResDto> getAllDto();
    HolidayResDto getByIdDto(int id);
    int addDto(HolidayReqDto admin);
    void updateDto(HolidayReqDto admin,int id);
}
