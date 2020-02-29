package com.xiaobao.gmall.user.controller;

import com.xiaobao.gmall.bean.UserInfo;
import com.xiaobao.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器层
 * @author lbw
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public List<UserInfo> findAll(){
        return userService.findAll();
    }
}
