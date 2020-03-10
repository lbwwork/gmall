package com.xiaobao.gmall.manage.mapper;

import com.xiaobao.gmall.bean.SpuImage;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lbw
 */
public interface SpuImageMapper extends Mapper<SpuImage> {
    /**
     * 批量保存SpuImage
     * @param spuImages
     */
    void insertSpuImages(List<SpuImage> spuImages);
}
