package com.xiaobao.gmall.user.service.impl;

import com.xiaobao.gmall.bean.UserAddress;
import com.xiaobao.gmall.bean.UserInfo;
import com.xiaobao.gmall.service.UserService;
import com.xiaobao.gmall.user.mapper.UserAddressMapper;
import com.xiaobao.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 * @author lbw
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserAddress> findAddressByUserId(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return userAddressMapper.select(userAddress);
    }
}
