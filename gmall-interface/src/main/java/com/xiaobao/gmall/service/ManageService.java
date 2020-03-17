package com.xiaobao.gmall.service;

import com.xiaobao.gmall.bean.BaseAttrInfo;
import com.xiaobao.gmall.bean.BaseAttrValue;
import com.xiaobao.gmall.bean.BaseCatalog1;
import com.xiaobao.gmall.bean.BaseCatalog2;
import com.xiaobao.gmall.bean.BaseCatalog3;
import com.xiaobao.gmall.bean.BaseSaleAttr;
import com.xiaobao.gmall.bean.SkuInfo;
import com.xiaobao.gmall.bean.SpuImage;
import com.xiaobao.gmall.bean.SpuInfo;
import com.xiaobao.gmall.bean.SpuSaleAttr;

import java.util.List;

/**
 * 后台管理接口
 * @author lbw
 */
public interface ManageService {
    /**
     * 获取所有一级分类
     * @return 一级分类的集合
     */
    List<BaseCatalog1> getCatalog1();

    /**
     * 获取一级分类下的二级分类
     * @param catalog1Id    一级分类id
     * @return  对应的二级分类集合
     */
    List<BaseCatalog2> getCatalog2(String catalog1Id);

    /**
     * 获取二级分类下的三级分类
     * @param catalog2Id    二级分类id
     * @return  对应的三级分类集合
     */
    List<BaseCatalog3> getCatalog3(String catalog2Id);

    /**
     * 获取三级分类下对应的平台属性
     * @param catalog3Id    三级分类id
     * @return  对应的平台属性集合
     */
    List<BaseAttrInfo> getAttrList(String catalog3Id);

    /**
     * 保存或修改平台属性
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 修改平台属性值回显
     * @param attrId
     * @return
     */
    List<BaseAttrValue> getAttrValueList(String attrId);

    /**
     * 获取主键对应的平台属性
     * @param attrId
     * @return
     */
    BaseAttrInfo getAttrInfo(String attrId);

    /**
     * 根据三级分类id获取spu集合
     * @param catalog3Id
     * @return
     */
    List<SpuInfo> spuList(String catalog3Id);

    /**
     * 获取所有的销售属性
     * @return
     */
    List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * 保存spuInfo
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 根据spuId获取spuSaleAttr集合
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> spuSaleAttrList(String spuId);

    /**
     * 根据spuId获取SpuImage集合
     * @param supId
     * @return
     */
    List<SpuImage> spuImageList(String supId);

    /**
     * 保存skuInfo
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 根据skuId查询skuInfo
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(String skuId);
}
