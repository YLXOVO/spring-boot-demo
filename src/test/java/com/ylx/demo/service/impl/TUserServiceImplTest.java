package com.ylx.demo.service.impl;

import com.ylx.demo.model.dto.AddUserDTO;
import com.ylx.demo.model.po.SearchPO;
import com.ylx.demo.service.TUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TUserServiceImplTest {
    @Resource
    private TUserService tUserService;

    @Test
    public void getByUsername() {
        SearchPO searchPO = tUserService.getByUsername("张三");
        Assertions.assertNotNull(searchPO);
        System.out.println(searchPO.getUsername());
    }

    @Test
    public void addUser() {
        AddUserDTO addUserDTO = new AddUserDTO();
        addUserDTO.setUsername("赵四");
        addUserDTO.setPassword("123");
        addUserDTO.setDeptName("宣传部");

        long l = tUserService.addUser(addUserDTO);
        System.out.println(l);
    }
}