package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@Tag(name = "管理员模块")
public class AdminController {

    @Resource
    AdminService adminService;

    /**
     *查询所有admin表数据
    */
    @Operation(summary = "admin列表",description = "查询admin所有信息")
    @GetMapping(value = "/findAll")
    public Result findAll (){
      List<Admin> list =  adminService.findAll();
      return Result.success(list);
    }
}
