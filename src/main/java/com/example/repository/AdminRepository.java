package com.example.repository;

import com.example.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByUserName(String userName);

    boolean existsByUserName(String userName);

}
