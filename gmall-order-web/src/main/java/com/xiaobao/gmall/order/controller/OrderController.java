package com.xiaobao.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobao.gmall.bean.UserAddress;
import com.xiaobao.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单控制器层
 * @author lbw
 */
@Controller
public class OrderController {
    /**
     * dubbo消费者注解
     */
    @Reference
    private UserService userService;
    @RequestMapping("trade")
    public String trade(){
        return "index";
    }

    @RequestMapping("findAddressByUserId")
    @ResponseBody
    public List<UserAddress> findAddressByUserId(String userId){
        return userService.findAddressByUserId(userId);
    }
}
