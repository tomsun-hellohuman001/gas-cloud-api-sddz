package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.GasOrdersMapper;
import com.sddz.gasstation.dto.GasOrdersDto;
import com.sddz.gasstation.entity.PC.GasOrders;
import com.sddz.gasstation.param.GasOrderAddParam;
import com.sddz.gasstation.param.GasOrderQueryParam;
import com.sddz.gasstation.utils.BeanConverter;
import com.ycy.common.web.PageableRestResult;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/gasOrder")
public class GasOrderController {
    @Autowired
    private GasOrdersMapper gasOrdersMapper;

    @PostMapping("/add")
    @ApiOperation("添加加油订单")
    public RestResult addOrder(@RequestBody GasOrderAddParam param) {
        GasOrders gasOrders = BeanConverter.convertBean(param, GasOrders.class);
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 该方法先不用了
        gasOrders.setPayTime(new Date());
        return RestResult.success(gasOrdersMapper.insert(gasOrders));
    }

    @GetMapping(value = "/selectGasOrderByCondition")
    @ResponseBody
    @ApiOperation("查询加油记录")
    public PageableRestResult<List<GasOrdersDto>> selectByCondition(GasOrderQueryParam param){
        return PageableRestResult.success(gasOrdersMapper.selectGasOrderByCondition(param), 0, gasOrdersMapper.selectCountGasOrderByCondition(param));
    }

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
