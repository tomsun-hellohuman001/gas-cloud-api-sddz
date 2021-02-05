package com.sddz.gasstation.controller;

import com.sddz.gasstation.param.ChinaAreaQueryParam;
import com.sddz.gasstation.service.AdminChinaAreaService;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminChinaAreaController")
@RequestMapping("/admin/chinaArea")
@Api(tags = "行政区域接口")
public class AdminChinaAreaController {

    @Autowired
    private AdminChinaAreaService adminChinaAreaService;

    @ApiOperation("登陆接口")
    @PostMapping("getRegionByPid")
    public RestResult getRegionByPid(@RequestBody ChinaAreaQueryParam param){
        return RestResult.success(adminChinaAreaService.getRegionByPid(param));
    }

}
