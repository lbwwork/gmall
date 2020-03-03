package com.xiaobao.gmall.service;

import com.xiaobao.gmall.bean.BaseAttrInfo;
import com.xiaobao.gmall.bean.BaseCatalog1;
import com.xiaobao.gmall.bean.BaseCatalog2;
import com.xiaobao.gmall.bean.BaseCatalog3;

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
}
