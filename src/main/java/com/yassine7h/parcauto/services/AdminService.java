package com.yassine7h.parcauto.services;

import com.yassine7h.parcauto.dtos.AdminReqDto;
import com.yassine7h.parcauto.dtos.AdminResDto;
import com.yassine7h.parcauto.exceptions.ResourceExistException;
import com.yassine7h.parcauto.exceptions.ResourceNotFoundException;
import com.yassine7h.parcauto.mappers.AdminMapper;
import com.yassine7h.parcauto.models.Admin;
import com.yassine7h.parcauto.repositories.AdminRepository;
import com.yassine7h.parcauto.services.interfaces.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getById(int id) {
        return adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id,Admin.class));
    }

    @Override
    public int add(Admin admin) {
        Optional<Admin> adminOptional=adminRepository.findById(admin.getId());
        if(adminOptional.isPresent()) throw new ResourceExistException(admin.getId(),Admin.class);
        Admin addedAdmin=adminRepository.save(admin);
        return addedAdmin.getId();
    }

    @Override
    public void update(Admin admin,int id) {
        Optional<Admin> adminOptional=adminRepository.findById(id);
        if(!adminOptional.isPresent()) throw new ResourceNotFoundException(admin.getId(),Admin.class);
        admin.setId(id);
        adminRepository.save(admin);
    }

    @Override
    public void delete(int id) {
        Optional<Admin> adminOptional=adminRepository.findById(id);
        if(!adminOptional.isPresent()) throw new ResourceNotFoundException(id,Admin.class);
        adminRepository.deleteById(id);
    }

    @Override
    public List<AdminResDto> getAllDto() {
        return  getAll().stream().map(i->adminMapper.toAdminResDto(i)).collect(Collectors.toList());
    }

    @Override
    public AdminResDto getByIdDto(int id) {
        return adminMapper.toAdminResDto( getById(id));
    }

    @Override
    public int addDto(AdminReqDto admin) {
        return add(adminMapper.toAdmin(admin));
    }

    @Override
    public void updateDto(AdminReqDto admin,int id) {
        update(adminMapper.toAdmin(admin),id);
    }
}
