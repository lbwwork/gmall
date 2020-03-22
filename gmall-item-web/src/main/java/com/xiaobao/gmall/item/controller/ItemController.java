package com.xiaobao.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobao.gmall.bean.SkuImage;
import com.xiaobao.gmall.bean.SkuInfo;
import com.xiaobao.gmall.bean.SpuSaleAttr;
import com.xiaobao.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lbw
 */
@Controller
public class ItemController {
    @Reference
    private ManageService manageService;
    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, HttpServletRequest request){
        //根据skuId获取数据
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        //查询销售属性和销售属性集合
        List<SpuSaleAttr> saleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);
        skuInfo.setSpuSaleAttrs(saleAttrList);
        request.setAttribute("skuInfo",skuInfo);
        return "item";
    }
}
