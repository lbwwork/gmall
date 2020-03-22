package com.xiaobao.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobao.gmall.bean.BaseAttrInfo;
import com.xiaobao.gmall.bean.BaseAttrValue;
import com.xiaobao.gmall.bean.BaseCatalog1;
import com.xiaobao.gmall.bean.BaseCatalog2;
import com.xiaobao.gmall.bean.BaseCatalog3;
import com.xiaobao.gmall.bean.BaseSaleAttr;
import com.xiaobao.gmall.bean.SkuAttrValue;
import com.xiaobao.gmall.bean.SkuImage;
import com.xiaobao.gmall.bean.SkuInfo;
import com.xiaobao.gmall.bean.SkuSaleAttrValue;
import com.xiaobao.gmall.bean.SpuImage;
import com.xiaobao.gmall.bean.SpuInfo;
import com.xiaobao.gmall.bean.SpuSaleAttr;
import com.xiaobao.gmall.bean.SpuSaleAttrValue;
import com.xiaobao.gmall.manage.mapper.BaseAttrInfoMapper;
import com.xiaobao.gmall.manage.mapper.BaseAttrValueMapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog1Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog2Mapper;
import com.xiaobao.gmall.manage.mapper.BaseCatalog3Mapper;
import com.xiaobao.gmall.manage.mapper.BaseSaleAttrMapper;
import com.xiaobao.gmall.manage.mapper.SkuAttrValueMapper;
import com.xiaobao.gmall.manage.mapper.SkuImageMapper;
import com.xiaobao.gmall.manage.mapper.SkuInfoMapper;
import com.xiaobao.gmall.manage.mapper.SkuSaleAttrValueMapper;
import com.xiaobao.gmall.manage.mapper.SpuImageMapper;
import com.xiaobao.gmall.manage.mapper.SpuInfoMapper;
import com.xiaobao.gmall.manage.mapper.SpuSaleAttrMapper;
import com.xiaobao.gmall.manage.mapper.SpuSaleAttrValueMapper;
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
    @Autowired
    private BaseSaleAttrMapper saleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
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
        List<BaseAttrInfo> attrInfos = attrInfoMapper.select(baseAttrInfo);
        //此处为快速开发，避免繁琐的业务，为快速学习后面技术，故循环中查询数据库，实际请勿这样操作
        for (BaseAttrInfo attrInfo : attrInfos) {
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(attrInfo.getId());
            List<BaseAttrValue> attrValues = attrValueMapper.select(baseAttrValue);
            attrInfo.setAttrValueList(attrValues);
        }
        return attrInfos;
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

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return saleAttrMapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuInfo spuInfo) {
        if (StringUtils.isBlank(spuInfo.getId())){
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        }else {
            spuInfoMapper.updateByPrimaryKeySelective(spuInfo);
        }
        //图片先删后加
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuInfo.getId());
        spuImageMapper.delete(spuImage);
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage image : spuImageList) {
            image.setSpuId(spuInfo.getId());
            image.setId(null);
        }
        spuImageMapper.insertSpuImages(spuImageList);
        // 销售属性 删除，插入
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuInfo.getId());
        spuSaleAttrMapper.delete(spuSaleAttr);

        // 销售属性值 删除，插入
        SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
        spuSaleAttrValue.setSpuId(spuInfo.getId());
        spuSaleAttrValueMapper.delete(spuSaleAttrValue);

        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr saleAttr : spuSaleAttrList) {
            saleAttr.setId(null);
            saleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insertSelective(saleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue saleAttrValue : spuSaleAttrValueList) {
                saleAttrValue.setId(null);
                saleAttrValue.setSpuId(spuInfo.getId());
                saleAttrValue.setSaleAttrId(saleAttr.getId());
            }
            spuSaleAttrValueMapper.insertSpuSaleAttrValue(spuSaleAttrValueList);
        }
    }

    @Override
    public List<SpuSaleAttr> spuSaleAttrList(String spuId) {
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        List<SpuSaleAttr> spuSaleAttrs = spuSaleAttrMapper.select(spuSaleAttr);
        for (SpuSaleAttr saleAttr : spuSaleAttrs) {
            SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
            spuSaleAttrValue.setSpuId(spuId);
            List<SpuSaleAttrValue> spuSaleAttrValues = spuSaleAttrValueMapper.select(spuSaleAttrValue);
            saleAttr.setSpuSaleAttrValueList(spuSaleAttrValues);
        }
        return spuSaleAttrs;
    }

    @Override
    public List<SpuImage> spuImageList(String supId) {
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(supId);
        return spuImageMapper.select(spuImage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insertSelective(skuInfo);
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (skuImageList != null && skuImageList.size() > 0){
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(skuInfo.getId());
                skuImageMapper.insertSelective(skuImage);
            }
        }
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (skuAttrValueList != null && skuAttrValueList.size() > 0){
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValueMapper.insertSelective(skuAttrValue);
            }
        }
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (skuSaleAttrValueList != null && skuSaleAttrValueList.size() > 0){
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
            }
        }
    }

    @Override
    public SkuInfo getSkuInfo(String skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        //获取图片列表
        List<SkuImage> skuImages = getSkuImageList(skuId);
        skuInfo.setSkuImageList(skuImages);
        return skuInfo;
    }

    @Override
    public List<SkuImage> getSkuImageList(String skuId) {
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        return skuImageMapper.select(skuImage);
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(SkuInfo skuInfo) {
        return   spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuInfo.getId(),skuInfo.getSpuId());
    }


}
