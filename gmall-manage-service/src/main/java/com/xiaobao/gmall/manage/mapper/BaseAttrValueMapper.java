package com.xiaobao.gmall.manage.mapper;

import com.xiaobao.gmall.bean.BaseAttrValue;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 平台属性mapper
 * @author lbw
 */
public interface BaseAttrValueMapper extends Mapper<BaseAttrValue> {

    /**
     * 插入多条平台属性值
     * @param attrValues
     */
    void insertAttrValues(List<BaseAttrValue> attrValues);
}
