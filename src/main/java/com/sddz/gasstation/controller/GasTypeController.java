package com.sddz.gasstation.controller;

import com.sddz.gasstation.dto.GasTypeDto;
import com.sddz.gasstation.param.GasTypeAddParam;
import com.sddz.gasstation.param.GasTypeEditParam;
import com.sddz.gasstation.param.GasTypeQueryParam;
import com.sddz.gasstation.service.GasTypeService;
import com.ycy.common.web.PageableRestResult;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <Description> 油号 <br>
 *
 * @author zhaopengwei<br>
 */
@RestController
@RequestMapping("/admin/gasType")
@Api(tags = "油号接口")
public class GasTypeController {

    @Autowired
    private GasTypeService gasTypeService;

    @GetMapping("/list")
    @ApiOperation("油号列表")
    public PageableRestResult<List<GasTypeDto>> queryByPage(GasTypeQueryParam param) {
        Page<GasTypeDto> page = gasTypeService.queryByPage(param);
        return PageableRestResult.success(page.getContent(), page.getTotalPages(), page.getTotalElements());
    }

    @PostMapping("/add")
    @ApiOperation("新增油号")
    public RestResult addGasType(@RequestBody @Validated GasTypeAddParam param) {
        gasTypeService.addGasType(param);
        return RestResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation("编辑油号")
    public RestResult editGasType(@RequestBody @Validated GasTypeEditParam param) {
        gasTypeService.updateGasType(param);
        return RestResult.success();
    }

    @GetMapping("/all")
    @ApiOperation("获取所有油号")
    public RestResult<List<GasTypeDto>> all() {
        List<GasTypeDto> list = gasTypeService.all();
        return RestResult.success(list);
    }

    @PostMapping("/remove/{id}")
    @ApiOperation("删除油号")
    public RestResult removeGasType(@PathVariable Long id) {
        gasTypeService.removeGasType(id);
        return RestResult.success();
    }
}
