package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.GasStationMapper;
import com.sddz.gasstation.dto.GasStationDto;
import com.sddz.gasstation.entity.PC.GasStation;
import com.sddz.gasstation.param.GasStationAddParam;
import com.sddz.gasstation.param.GasStationEditParam;
import com.sddz.gasstation.param.GasStationQueryParam;
import com.sddz.gasstation.param.IdPostParam;
import com.sddz.gasstation.service.GasStationService;
import com.ycy.common.web.PageableRestResult;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gasStation")
public class GasStationController {
    @Autowired
    private GasStationMapper gasStationMapper;

    @Autowired
    private GasStationService gasStationService;

    @GetMapping(value = "/showGasStation/{id}")
    @ResponseBody
    public GasStation select(@PathVariable("id") Long id){
        return gasStationMapper.selectByPrimaryKey(id);
    }

    @GetMapping(value = "/showGasStationEx")
    @ResponseBody
    public RestResult<List<GasStation>> selectEx(Long id){
        return RestResult.success(gasStationMapper.selectByPrimaryKeyEx(id));
    }

//    @GetMapping(value = "/showGasStationbyCondition")
//    @ResponseBody
//    @ApiOperation("查询加油站")
//    public RestResult<List<GasStationDto>> selectByCondition(GasStationQueryParam param){
//        return RestResult.success(gasStationMapper.selectByCondition(param));
//    }

    @GetMapping(value = "/showGasStationbyCondition")
    @ResponseBody
    @ApiOperation("查询加油站")
    public PageableRestResult<List<GasStationDto>> selectByCondition(GasStationQueryParam param){
        return PageableRestResult.success(gasStationMapper.selectByCondition(param), 0, gasStationMapper.selectCountByCondition(param));
    }

    @PostMapping("/add")
    @ApiOperation("添加加油站")
    public RestResult addStation(@RequestBody GasStationAddParam param) {
        return RestResult.success(gasStationService.addGasStation(param));
    }

    @PostMapping("/edit")
    @ApiOperation("编辑加油站")
    public RestResult editStation(@RequestBody GasStationEditParam param) {
        gasStationService.updateGasStation(param);
        return RestResult.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除加油站")
    public RestResult delStation(@RequestBody IdPostParam param){
        return RestResult.success(gasStationMapper.deleteByPrimaryKey(param.getId()));
    }
}
