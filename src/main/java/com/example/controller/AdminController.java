package com.example.controller;

import com.example.common.PageInfo;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@Tag(name = "管理员模块")
public class AdminController {

    @Resource
    AdminService adminService;


    /**
    * 新增或者更新数据
    */
    @Operation(summary = "新增和更新管理员",description = "新增和更新接口，提供json对象数据")
    @PostMapping(value = "/add")
    public Result add(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }

    /**
     * 删除用户
     */
    @Operation(summary = "删除管理员",description = "删除用户，提供json对象数据")
    @GetMapping(value = "/delete/{id}")
    public Result delete(@PathVariable String id) {
        String msg = adminService.delete(id);
        return Result.success(msg);
    }

    /**
     *查询所有admin表数据
    */
    @Operation(summary = "admin列表",description = "查询admin所有信息")
    @GetMapping(value = "/findAll")
    public Result findAll (){
      List<Admin> list =  adminService.findAll();
      return Result.success(list);
    }


    /**
     * 分页查询admin表数据
     */
    @Operation(summary = "admin列表", description = "查询admin分页查询信息")
    @PostMapping(value = "/findPage")
    public Result findPage(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> list = adminService.findPage(name, pageNum, pageSize);
        return Result.success(list);
    }

    /**
     * 分页查询admin表数据
     *
     * @param Admin
     */
    @Operation(summary = "admin列表", description = "查询admin分页查询信息")
    @PostMapping(value = "/findPage1")
    public Result findPage1(Admin Admin) {
        PageInfo<Admin> list = adminService.findPage1(Admin);
        return Result.success(list);
    }

    /**
     * 分页查询admin表数据
     */
    @Operation(summary = "admin列表", description = "查询admin分页查询信息")
    @PostMapping(value = "/findPage2")
    public Result findPage2(Admin admin) {
        PageInfo<Admin> list = adminService.findPage2(admin);
        return Result.success(list);
    }


}



