package com.xiaobao.gmall.order.controller;

import com.xiaobao.gmall.bean.UserAddress;
import com.xiaobao.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 订单控制器层
 * @author lbw
 */
@Controller
public class OrderController {
    @Autowired
    private UserService userService;
    @RequestMapping("trade")
    public String trade(){
        return "index";
    }

    @RequestMapping("findAddressByUserId")
    public List<UserAddress> findAddressByUserId(String userId){
        return userService.findAddressByUserId(userId);
    }
}
