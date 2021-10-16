package com.guigu.aclservice.controller;


import com.guigu.aclservice.entity.Permission;
import com.guigu.aclservice.service.PermissionService;

import com.guigu.commonutils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Api(description="PermissionController")
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("findAll")
    public ReturnResult indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        System.out.println(list.toString());
        return ReturnResult.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public ReturnResult remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return ReturnResult.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ReturnResult doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return ReturnResult.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public ReturnResult toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return ReturnResult.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ReturnResult save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ReturnResult.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ReturnResult updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return ReturnResult.ok();
    }

}

