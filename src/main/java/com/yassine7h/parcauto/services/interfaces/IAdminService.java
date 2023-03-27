package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.models.Admin;

import java.util.List;

public interface IAdminService {
    public List<Admin> getAll();
    public Admin getById(int id);
    public int add(Admin driver);
    public void update(Admin driver);
    public void delete(int id);
}
