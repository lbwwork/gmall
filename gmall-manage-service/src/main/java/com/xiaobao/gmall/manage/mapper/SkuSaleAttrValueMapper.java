package com.xiaobao.gmall.manage.mapper;

import com.xiaobao.gmall.bean.SkuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lbw
 */
public interface SkuSaleAttrValueMapper extends Mapper<SkuSaleAttrValue> {
    /**
     * xxx
     * @param spuId
     * @return
     */
    List<SkuSaleAttrValue> selectSkuSaleAttrValueListBySpu(String spuId);
}
