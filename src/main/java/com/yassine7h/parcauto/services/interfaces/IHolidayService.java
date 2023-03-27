package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Holiday;

import java.util.List;

public interface IHolidayService {
    public List<Holiday> getAll();
    public Holiday getById(int id);
    public int add(Holiday driver);
    public void update(Holiday driver);
    public void delete(int id);
}
