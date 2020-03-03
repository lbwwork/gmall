package com.xiaobao.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobao.gmall.bean.BaseAttrInfo;
import com.xiaobao.gmall.bean.BaseAttrValue;
import com.xiaobao.gmall.bean.BaseCatalog1;
import com.xiaobao.gmall.bean.BaseCatalog2;
import com.xiaobao.gmall.bean.BaseCatalog3;
import com.xiaobao.gmall.manage.mapper.BaseAttrInfoMapper;
import com.xiaobao.gmall.manage.mapper.BaseAttrValueMapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog1Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog2Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog3Mapper;
import com.xiaobao.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 后台管理实现类
 * @author lbw
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BaseCatalog1Mapper catalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper catalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper catalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper attrInfoMapper;
    @Autowired
    private BaseAttrValueMapper attrValueMapper;
    @Override
    public List<BaseCatalog1> getCatalog1() {
        return catalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return catalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return catalog3Mapper.select(baseCatalog3);
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return attrInfoMapper.select(baseAttrInfo);
    }
}
