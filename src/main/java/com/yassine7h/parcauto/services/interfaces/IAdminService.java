package com.yassine7h.parcauto.services.interfaces;

import com.yassine7h.parcauto.dtos.AdminReqDto;
import com.yassine7h.parcauto.dtos.AdminResDto;
import com.yassine7h.parcauto.models.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAll();

    Admin getById(int id);

    int add(Admin admin);

    void update(Admin admin,int id);

    void delete(int id);
    List<AdminResDto> getAllDto();
    AdminResDto getByIdDto(int id);
    int addDto(AdminReqDto admin);
    void updateDto(AdminReqDto admin,int id);
}
