package com.example.service;



import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.repository.AdminRepository;
import org.springframework.data.domain.Example;
import jakarta.annotation.Resource;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Resource
    AdminRepository adminRepository;

    public void save(Admin admin) {
        if (admin.getId() == null) { //根据id判断是否为新增还是更新
//           判断用户名是否重复
             Admin ddAdmin = adminRepository.findByUserName(admin.getUserName());
             if (ddAdmin != null) {
                 throw new CustomException("账号重复");
             }
            if (admin.getPassWord() == null || admin.getPassWord().isEmpty()) {
                throw new CustomException("未填写密码");
            }
            if (admin.getName() == null || admin.getName().isEmpty()) {
                admin.setName("管理员");
            }
            admin.setRole("管理员");
            adminRepository.save(admin);
        }else{
            // 1. 检查待更新的用户是否存在
            Admin existingAdmin = adminRepository.findById(admin.getId())
                    .orElseThrow(() -> new CustomException("用户id不存在"));

            // 2. 处理用户名唯一性（如果尝试修改用户名）
            if (admin.getUserName() != null && !admin.getUserName().equals(existingAdmin.getUserName())) {
                // 新用户名不能与其他用户重复（排除自身）
                if (adminRepository.existsByUserName(admin.getUserName())) {
                    throw new CustomException("用户名已被占用，不可更改");
                }
            }
           Admin ddAdmin = adminRepository.findById(admin.getId()).orElse(null);
            if (ddAdmin != null) {
                BeanUtil.copyProperties(admin, ddAdmin, CopyOptions.create().setIgnoreNullValue(true));
                admin = ddAdmin;
            }else{
                throw new CustomException("数据不存在");
            }


//              // 3. 动态复制非空字段到 existingAdmin
//        if (admin.getUserName() != null) {
//            existingAdmin.setUserName(admin.getUserName());
//        }
//        if (admin.getPassWord() != null && !admin.getPassWord().isEmpty()) {
//            existingAdmin.setPassWord(admin.getPassWord()); // 注意：密码建议加密存储
//        }
//        if (admin.getName() != null) {
//            existingAdmin.setName(admin.getName());
//        }
//        if (admin.getAvatar() != null) {
//            existingAdmin.setAvatar(admin.getAvatar());
//        }
//        // role 通常不允许普通更新，或者按需处理
//        // if (admin.getRole() != null) { existingAdmin.setRole(admin.getRole()); }
//
//        // 4. 保存更新后的对象
//        adminRepository.save(existingAdmin);
        }
        adminRepository.save(admin);
    }


    public String delete(String ids) {
        if (ids == null|| ids.isEmpty()) {
            throw new CustomException("请至少提供一个用户ID");
        }
        String[] idArray = ids.split(",");
        List<Integer> idList = Arrays.stream(idArray)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("idList = "+idList);
        // 检查所有 ID 是否存在（可根据业务决定是否必须全部存在）
        List<Admin> existingAdmins = adminRepository.findAllById(idList);
        List<Integer> existingIds = existingAdmins.stream().map(Admin::getId).collect(Collectors.toList());

        if (existingAdmins.size() != idList.size()) {
            // 找出不存在的 ID
            List<Integer> notExistIds = idList.stream()
                    .filter(id -> !existingIds.contains(id))
                    .collect(Collectors.toList());
            adminRepository.deleteAllById(existingIds);

            String deletedStr = CollUtil.join(existingIds, ",");
            String notFoundStr = CollUtil.join(notExistIds, ",");
            return "已删除用户: " + deletedStr + "，以下用户ID不存在: " + notFoundStr;
        }else {
            adminRepository.deleteAllById(existingIds);
            return "已删除用户: " + existingIds;
        }
    }

    public List<Admin> findAll () {
       return adminRepository.findAll();
    };




}
