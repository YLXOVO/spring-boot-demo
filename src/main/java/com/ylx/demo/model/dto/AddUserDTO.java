package com.ylx.demo.model.dto;

import lombok.Data;

/**
 * 添加用户 数据传输对象
 *
 */
@Data
public class AddUserDTO {
    private Integer userCode;
    private String username;
    private String password;
    private String deptName;
}
