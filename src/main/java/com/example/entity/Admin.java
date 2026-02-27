package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 管理员信息实体类，对应数据库表 admin
 */
@Data                // 生成 getter/setter、equals、hashCode、toString 等方法
//@NoArgsConstructor   // 生成无参构造方法
//@AllArgsConstructor  // 生成全参构造方法
@Entity
@DynamicUpdate
@Table(name = "admin")
public class Admin {
    /**
     * ID主键，自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 密码
     */
    @Column(name = "password")
    private String passWord;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 头像URL或路径
     */
    @Column(name = "avatar")
    private String avatar;


    /**
     * 角色（如：管理员、超级管理员等）
     */
    @Column(name = "role")
    private String role;

    /**
     * 登录账号
     */
    @Column(name = "username")
    private String userName;

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;


}