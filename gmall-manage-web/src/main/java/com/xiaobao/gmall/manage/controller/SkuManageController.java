package com.xiaobao.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobao.gmall.bean.SkuInfo;
import com.xiaobao.gmall.bean.SpuImage;
import com.xiaobao.gmall.bean.SpuSaleAttr;
import com.xiaobao.gmall.service.ManageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lbw
 */
@RestController
@CrossOrigin
public class SkuManageController {

    @Reference
    private ManageService manageService;
    @RequestMapping("saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo){
        if (skuInfo!=null){
            manageService.saveSkuInfo(skuInfo);
        }

    }
}
