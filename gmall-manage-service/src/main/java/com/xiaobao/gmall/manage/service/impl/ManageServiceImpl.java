package com.xiaobao.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobao.gmall.bean.BaseAttrInfo;
import com.xiaobao.gmall.bean.BaseAttrValue;
import com.xiaobao.gmall.bean.BaseCatalog1;
import com.xiaobao.gmall.bean.BaseCatalog2;
import com.xiaobao.gmall.bean.BaseCatalog3;
import com.xiaobao.gmall.bean.SpuInfo;
import com.xiaobao.gmall.manage.mapper.BaseAttrInfoMapper;
import com.xiaobao.gmall.manage.mapper.BaseAttrValueMapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog1Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog2Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog3Mapper;
import com.xiaobao.gmall.manage.mapper.SpuInfoMapper;
import com.xiaobao.gmall.service.ManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private SpuInfoMapper spuInfoMapper;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if (StringUtils.isNotBlank(baseAttrInfo.getId())){
            attrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else {
            attrInfoMapper.insertSelective(baseAttrInfo);
        }
        //先清空所有的值
        BaseAttrValue baseAttrValueDel = new BaseAttrValue();
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        attrValueMapper.delete(baseAttrValueDel);
        //重新添加
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList != null && attrValueList.size() > 0){
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setAttrId(baseAttrInfo.getId());
            }
            attrValueMapper.insertAttrValues(attrValueList);
        }
    }

    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        return attrValueMapper.select(baseAttrValue);
    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = attrInfoMapper.selectByPrimaryKey(attrId);
        if (baseAttrInfo != null){
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            List<BaseAttrValue> attrValues = attrValueMapper.select(baseAttrValue);
            baseAttrInfo.setAttrValueList(attrValues);
        }
        return baseAttrInfo;
    }

    @Override
    public List<SpuInfo> spuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        return spuInfoMapper.select(spuInfo);
    }


}
