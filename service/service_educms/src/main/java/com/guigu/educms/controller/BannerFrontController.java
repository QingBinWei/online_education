package com.guigu.educms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.educms.entity.CrmBanner;
import com.guigu.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-04
 */
@Api(description="前台banner")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/educms/banner")
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "查询z最近3条banner")
    @GetMapping("getAllBanner")
    public ReturnResult getAllBanner() {
        List<CrmBanner>crmBanners=crmBannerService.getAllBanner();
        return ReturnResult.ok().data("items",crmBanners);
    }
    @ApiOperation(value = "查询课程")
    @GetMapping("getCourse")
    public ReturnResult getCourse() {
        List<CrmBanner>crmBanners=crmBannerService.getAllBanner();
        return ReturnResult.ok().data("items",crmBanners);
    }

}

