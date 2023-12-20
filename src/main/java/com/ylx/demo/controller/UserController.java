package com.ylx.demo.controller;

import com.ylx.demo.common.BaseResponse;
import com.ylx.demo.common.ErrorCode;
import com.ylx.demo.common.ResultUtils;
import com.ylx.demo.exception.BusinessException;
import com.ylx.demo.model.dto.AddUserDTO;
import com.ylx.demo.model.po.SearchPO;
import com.ylx.demo.model.vo.SearchVO;
import com.ylx.demo.service.TUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private TUserService tUserService;
    /**
     * 查询用户
     *
     * @param username
     * @param request
     * @return
     */
    @PostMapping("/search")
    public BaseResponse<SearchVO> searchUsers(String username , HttpServletRequest request) {
        if (username == null || username.equals("")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名不能为空");
        }
        SearchPO searchPO = tUserService.getByUsername(username);
        SearchVO searchVO = new SearchVO();
        BeanUtils.copyProperties(searchPO,searchVO);
        return ResultUtils.success(searchVO);
    }


    @PostMapping("/add")
    public BaseResponse<Long> addUsers(@RequestBody AddUserDTO addUserDTO, HttpServletRequest request){
        if (addUserDTO == null || request ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        // 取出每一个元素
        String userCode = addUserDTO.getUserCode().toString();
        String username = addUserDTO.getUsername();
        String password = addUserDTO.getPassword();
        String deptName = addUserDTO.getDeptName();
        if (StringUtils.isAnyBlank(username,userCode,password,deptName)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"数据为空");
        }
        long addUserId = tUserService.addUser(addUserDTO, request);
        return ResultUtils.success(addUserId);

    }
}
