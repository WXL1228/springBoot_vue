package com.example.service;



import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    AdminRepository adminRepository;

    public List<Admin> findAll () {
       return adminRepository.findAll();
    };


}
