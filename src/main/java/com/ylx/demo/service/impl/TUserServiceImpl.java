package com.ylx.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylx.demo.common.ErrorCode;
import com.ylx.demo.exception.BusinessException;
import com.ylx.demo.model.TUser;
import com.ylx.demo.model.dto.AddUserDTO;
import com.ylx.demo.model.po.SearchPO;
import com.ylx.demo.service.TUserService;
import com.ylx.demo.mapper.TUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 杨乐昕
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-12-19 10:00:03
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{
    @Resource
    private TUserMapper tUserMapper;


    @Override
    public SearchPO getByUsername(String username){
        // 判断传入的参数是否为空
        if (username == null || username.equals("")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名不能为空");
        }
        // 查询该用户名的用户是否存在
        QueryWrapper<TUser> tUserQueryWrapper = new QueryWrapper<>();
        tUserQueryWrapper.eq("username",username);
        TUser tUser = tUserMapper.selectOne(tUserQueryWrapper);
        if (tUser == null){
            throw new BusinessException(ErrorCode.NOT_ERROR,"用户不存在");
        }
        String name = tUser.getUsername();
        String deptName = tUser.getDeptName();

        SearchPO searchPO = new SearchPO();
        searchPO.setUsername(name);
        searchPO.setDeptName(deptName);


        return searchPO;
    }

    @Override
    public long addUser(AddUserDTO addUserDTO) {
        // 判断传入的参数是否为空
        if (addUserDTO ==null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        // 取出各个字段，分别判断

        String username = addUserDTO.getUsername();
        String password = addUserDTO.getPassword();
        String deptName = addUserDTO.getDeptName();

        if (StringUtils.isAnyBlank(username,password,deptName)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }

        // 密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        // 判断用户是否存在
        QueryWrapper<TUser> tUserQueryWrapper = new QueryWrapper<>();
        tUserQueryWrapper.eq("username",username);
        Long count = tUserMapper.selectCount(tUserQueryWrapper);
        if (count >0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户已存在");
        }

        // 创建用户对象
        TUser tUser = new TUser();
        tUser.setPassword(encryptPassword);
        tUser.setUsername(username);
        tUser.setDeptName(deptName);
        int insertResult = tUserMapper.insert(tUser);
        if (insertResult < 1){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"创建用户失败");
        }

        return tUser.getUserCode();
    }




}




