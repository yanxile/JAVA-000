package com.homework.sat02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "t_emall_user",autoResultMap = true)
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private String userId;
    @TableField("user_name")
    private String userName;
    @TableField("nick_name")
    private String nickName;
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("mobile")
    private String mobile;
    @TableField("sex")
    private String sex;
    @TableField("birthday")
    private String birthday;
    @TableField("address")
    private String address;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
}
