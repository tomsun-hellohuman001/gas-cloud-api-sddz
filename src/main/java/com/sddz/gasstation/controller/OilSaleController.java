package com.sddz.gasstation.controller;

import com.sddz.gasstation.dto.OilSaleDto;
import com.sddz.gasstation.param.OilSaleAddParam;
import com.sddz.gasstation.param.OilSaleEditParam;
import com.sddz.gasstation.param.OilSaleQueryParam;
import com.sddz.gasstation.service.OilSaleService;
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
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@RestController
@RequestMapping("/admin/oilSale")
@Api(tags = "油品上架接口")
public class OilSaleController {

    @Autowired
    private OilSaleService oilSaleService;

    @GetMapping("/list")
    @ApiOperation("油品上架列表")
    public PageableRestResult<List<OilSaleDto>> queryByPage(OilSaleQueryParam param) {
        Page<OilSaleDto> page = oilSaleService.queryByPage(param);
        return PageableRestResult.success(page.getContent(), page.getTotalPages(), page.getTotalElements());
    }

    @PostMapping("/add")
    @ApiOperation("新增油品上架")
    public RestResult addOilSale(@RequestBody @Validated OilSaleAddParam param) {
        oilSaleService.addOilSale(param);
        return RestResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation("编辑油品上架")
    public RestResult editProduct(@RequestBody @Validated OilSaleEditParam param) {
        oilSaleService.updateOilSale(param);
        return RestResult.success();
    }

    @PostMapping("/remove/{id}")
    @ApiOperation("删除油品上架")
    public RestResult removeProduct(@PathVariable Long id) {
        oilSaleService.removeOilSale(id);
        return RestResult.success();
    }

    @PostMapping("/updateStatus")
    @ApiOperation("更新状态")
    public RestResult updateStatus(@RequestBody OilSaleEditParam param) {
        oilSaleService.updateStatus(param);
        return RestResult.success();
    }

//    @PostMapping("/send")
//    @ApiOperation("下发油价")
//    public RestResult sendOilPrice(@RequestBody @Validated OilSaleConfirmParam oilSaleConfirmParam){
//        oilSaleService.sendPriceMessage(oilSaleConfirmParam.getGasStationId(),oilSaleConfirmParam.getOilSaleId());
//        return RestResult.success();
//    }

}
