package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.GasOrdersMapper;
import com.sddz.gasstation.dto.GasOrdersDto;
import com.sddz.gasstation.param.GasOrderQueryParam;
import com.sddz.gasstation.service.GasStationService;
import com.ycy.common.web.PageableRestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gasOrderReport")
public class GasOrderReportController {
    @Autowired
    private GasOrdersMapper gasOrdersMapper;

    @Autowired
    private GasStationService gasStationService;

//    @GetMapping(value = "/showGasStation/{id}")
//    @ResponseBody
//    public GasStation select(@PathVariable("id") Long id){
//        return gasStationMapper.selectByPrimaryKey(id);
//    }
//
//    @GetMapping(value = "/showGasStationEx")
//    @ResponseBody
//    public RestResult<List<GasStation>> selectEx(Long id){
//        return RestResult.success(gasStationMapper.selectByPrimaryKeyEx(id));
//    }

    @GetMapping(value = "/dailyReport")
    @ResponseBody
    @ApiOperation("查询订单数据，按日汇总")
    public PageableRestResult<List<GasOrdersDto>> selectByCondition(GasOrderQueryParam param){
        return PageableRestResult.success(gasOrdersMapper.selectDailyReportByCondition(param), 0, 0);
    }
//
//    @PostMapping("/add")
//    @ApiOperation("添加加油站")
//    public RestResult addStation(@RequestBody GasStationAddParam param) {
//        return RestResult.success(gasStationService.addGasStation(param));
//    }
//
//    @PostMapping("/edit")
//    @ApiOperation("编辑加油站")
//    public RestResult editStation(@RequestBody GasStationEditParam param) {
//        gasStationService.updateGasStation(param);
//        return RestResult.success();
//    }
//
//    @PostMapping("/delete")
//    @ApiOperation("删除加油站")
//    public RestResult delStation(@RequestBody IdPostParam param){
//        return RestResult.success(gasStationMapper.deleteByPrimaryKey(param.getId()));
//    }
}
