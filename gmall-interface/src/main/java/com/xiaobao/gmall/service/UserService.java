package com.xiaobao.gmall.service;

import com.xiaobao.gmall.bean.UserAddress;
import com.xiaobao.gmall.bean.UserInfo;

import java.util.List;

/**
 * 用户接口
 * @author lbw
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 根据用户Id查询地址
     * @param userId    用户id
     * @return  地址集合
     */
    List<UserAddress> findAddressByUserId(String userId);
}
