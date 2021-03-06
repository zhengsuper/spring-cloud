package com.maple.cloud.manage.controller;


import com.maple.cloud.manage.service.IGatewayDefineService;
import com.maple.common.core.util.R;
import com.maple.system.api.bean.GatewayDefine;
import com.maple.system.api.ro.GatewayDefineRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 系统配置-gateway动态路由配置 前端控制器
 * </p>
 *
 * @author maple
 * @since 2019-07-30
 */
@Api(value = "动态路由配置模块")
@RestController
@RequestMapping("/gateway/define")
public class GatewayDefineController {

    @Autowired
    private IGatewayDefineService gatewayDefineService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @PostMapping
    public R add(@Valid @RequestBody GatewayDefineRo gatewayDefineRo) {
        boolean isOk = gatewayDefineService.add(gatewayDefineRo.toBean(GatewayDefine.class));
        return R.isOk(isOk, "新增网关路由");
    }

    @ApiOperation(value = "修改网关路由", notes = "根据id修改指定的网关路由")
    @ApiImplicitParam(name = "id", value = "网关路由id", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public R update(@PathVariable Integer id, @Valid @RequestBody GatewayDefineRo gatewayDefineRo) {
        GatewayDefine gatewayDefine = gatewayDefineRo.toBean(GatewayDefine.class);
        gatewayDefine.setId(id);
        return R.isOk(gatewayDefineService.update(gatewayDefine), "修改网关路由");
    }


    @ApiOperation(value = "删除网关路由", notes = "根据id删除指定的网关路由")
    @ApiImplicitParam(name = "id", value = "网关路由id", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public R delete(@PathVariable Integer id) {
        return R.isOk(gatewayDefineService.delete(id), "删除网关路由");
    }

    @GetMapping(value = "/{id}")
    public R get(@PathVariable Integer id) {
        return R.ok(gatewayDefineService.get(id));
    }

    @ApiOperation(value = "初始化Redis网关路由", notes = "初始化Redis网关路由")
    @GetMapping(value = "/init")
    public R initGateWayToRedis(){
        return R.isOk(gatewayDefineService.initGateWayToRedis(), "初始化网关路由信息");
    }

}

