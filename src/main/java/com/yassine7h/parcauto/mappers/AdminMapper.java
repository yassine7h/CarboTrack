package com.yassine7h.parcauto.mappers;

import com.yassine7h.parcauto.dtos.AdminReqDto;
import com.yassine7h.parcauto.dtos.AdminResDto;
import com.yassine7h.parcauto.dtos.DriverResDto;
import com.yassine7h.parcauto.models.Admin;
import com.yassine7h.parcauto.models.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminMapper {
    public abstract AdminResDto toAdminResDto(Admin driver);
    public abstract Admin toAdmin(AdminReqDto adminReqDto);
}
