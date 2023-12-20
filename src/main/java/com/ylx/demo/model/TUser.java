package com.ylx.demo.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class TUser implements Serializable {
    /**
     * 用户代码
     */
    @TableId(type = IdType.AUTO)
    private Integer userCode;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 机构代码

     */
    private String deptCode;

    /**
     * 机构名称
     */
    private String deptName;

    /**
     * 是否删除  1 - 有效 0 - 无效
     */
    @TableLogic
    private String mark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}