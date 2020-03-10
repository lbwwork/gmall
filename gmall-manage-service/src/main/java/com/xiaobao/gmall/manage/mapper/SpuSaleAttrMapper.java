package com.xiaobao.gmall.manage.mapper;

import com.xiaobao.gmall.bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lbw
 */
public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {
    /**
     * 批量插入
     * @param  spuSaleAttrs
     */
    void insertSpuSaleAttrs(List<SpuSaleAttr> spuSaleAttrs);
}
