package com.xiaobao.gmall.manage.mapper;

import com.xiaobao.gmall.bean.SpuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lbw
 */
public interface SpuSaleAttrValueMapper extends Mapper<SpuSaleAttrValue> {
    /**
     * 批量插入
     * @param spuSaleAttrValues
     */
    void insertSpuSaleAttrValue(List<SpuSaleAttrValue> spuSaleAttrValues);
}
