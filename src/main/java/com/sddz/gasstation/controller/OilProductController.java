package com.sddz.gasstation.controller;

import com.sddz.gasstation.dto.OilProductDto;
import com.sddz.gasstation.param.OilProductAddParam;
import com.sddz.gasstation.param.OilProductEditParam;
import com.sddz.gasstation.param.OilProductQueryParam;
import com.sddz.gasstation.service.OilProductService;
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
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@RestController
@RequestMapping("/admin/oilProduct")
@Api(tags = "油品接口")
public class OilProductController {

    @Autowired
    private OilProductService oilProductService;

    @GetMapping("/list")
    @ApiOperation("油品列表")
    public PageableRestResult<List<OilProductDto>> queryByPage(OilProductQueryParam param) {
        Page<OilProductDto> page = oilProductService.queryByPage(param);
        return PageableRestResult.success(page.getContent(), page.getTotalPages(), page.getTotalElements());
    }

    @PostMapping("/add")
    @ApiOperation("新增油品")
    public RestResult addProduct(@RequestBody @Validated OilProductAddParam param) {
        oilProductService.addProduct(param);
        return RestResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation("编辑油品")
    public RestResult editProduct(@RequestBody @Validated OilProductEditParam param) {
        oilProductService.updateProduct(param);
        return RestResult.success();
    }

    @PostMapping("/remove/{id}")
    @ApiOperation("删除油品")
    public RestResult removeProduct(@PathVariable Long id) {
        oilProductService.removeProduct(id);
        return RestResult.success();
    }

    @GetMapping("/all")
    @ApiOperation("获取所有油品")
    public RestResult<List<OilProductDto>> all() {
        List<OilProductDto> list = oilProductService.all();
        return RestResult.success(list);
    }

    @Deprecated
    @GetMapping("/getByCompany")
    @ApiOperation("获取油企下的所有油品")
    public RestResult<List<OilProductDto>> getCompanyOilProducts(@RequestParam Long companyId) {
        List<OilProductDto> productDtos = oilProductService.getByCompany(companyId);
        return RestResult.success(productDtos);
    }

    @GetMapping("/getByStation")
    @ApiOperation("获取油站下的所有油品")
    public RestResult<List<OilProductDto>> getStationOilProducts(@RequestParam Long stationId) {
        List<OilProductDto> productDtos = oilProductService.getByStation(stationId);
        return RestResult.success(productDtos);
    }
}
