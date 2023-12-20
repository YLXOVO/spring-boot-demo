package com.ylx.demo.service;

import com.ylx.demo.model.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylx.demo.model.dto.AddUserDTO;
import com.ylx.demo.model.po.SearchPO;

import javax.servlet.http.HttpServletRequest;


/**
* @author 杨乐昕
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2023-12-19 10:00:03
*/
public interface TUserService extends IService<TUser> {

    SearchPO getByUsername(String username);

    long addUser(AddUserDTO addUserDTO, HttpServletRequest request);
}
