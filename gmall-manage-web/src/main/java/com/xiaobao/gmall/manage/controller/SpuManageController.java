package com.xiaobao.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobao.gmall.bean.SpuImage;
import com.xiaobao.gmall.bean.SpuInfo;
import com.xiaobao.gmall.bean.SpuSaleAttr;
import com.xiaobao.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lbw
 */
@CrossOrigin
@Controller
public class SpuManageController {
    @Reference
    private ManageService manageService;
    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> spuList(String catalog3Id){
        return manageService.spuList(catalog3Id);
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<SpuSaleAttr> spuSaleAttrList(String spuId){
        return manageService.spuSaleAttrList(spuId);
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> spuImageList(String spuId){
        return manageService.spuImageList(spuId);
    }
}
