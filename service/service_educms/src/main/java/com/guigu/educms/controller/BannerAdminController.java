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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-04
 */
@Api(description="Banner管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/educms/bannerAdmin")
public class BannerAdminController {
    @Autowired
    CrmBannerService crmBannerService;
    @ApiOperation(value = "banner分页查询")
    @GetMapping("pageBanner/{page}/{limit}")
    public ReturnResult pageBanner(@PathVariable("page") long page, @PathVariable("limit") long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        QueryWrapper<CrmBanner> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        crmBannerService.page(pageBanner,queryWrapper);
        long total=pageBanner.getTotal();
        return ReturnResult.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }
    @CachePut(value = "banner", key="'crmBanners'")
    @ApiOperation(value = "添加banner")
    @PostMapping("addBanner")
    public ReturnResult addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return ReturnResult.ok();
    }
    @ApiOperation(value = "查询banner")
    @GetMapping("getBanner/{id}")
    public ReturnResult getBanner(@PathVariable String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return ReturnResult.ok().data("items",crmBanner);
    }
    @CachePut(value = "banner", key="'crmBanners'")
    @ApiOperation(value = "修改banner")
    @PostMapping("updateBanner")
    public ReturnResult updateBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return ReturnResult.ok();
    }
    @ApiOperation(value = "删除banner")
    @DeleteMapping("deleteBanner/{id}")
    public ReturnResult deleteBanner(@PathVariable String id) {
        crmBannerService.removeById(id);
        return ReturnResult.ok();
    }

}

