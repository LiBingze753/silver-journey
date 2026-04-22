package com.example.attendance.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String realName;   // 对应数据库列 real_name
    private String role;
    private Date createTime;   // 对应数据库列 create_time
}